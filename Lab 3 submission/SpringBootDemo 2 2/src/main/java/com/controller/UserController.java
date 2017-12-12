package com.controller;

import com.entity.CreateShareFolder;
import com.entity.Sharewith;
import com.entity.User;
import com.service.ShareFolderService;
import com.service.UserService;
import com.entity.GroupT;
import antlr.collections.List;

import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.html.HTMLParagraphElement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/routes")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ShareFolderService sharefolderservice;

    @PostMapping(path = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewUser(@RequestBody User user) {
        userService.addUser(user);

        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @GetMapping(path = "/allUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<User> getAllUsers() {

        return userService.getAllUsers();
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody String user, HttpSession session) {


        JSONObject jsonObject = new JSONObject(user);
        session.setAttribute("name", jsonObject.getString("username"));



        return new ResponseEntity(userService.login(jsonObject.getString("username"), jsonObject.getString("password")), HttpStatus.OK);
    }



    @PostMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    File[] getAllUsers1(HttpSession session) {

        return userService.getAllUsers1(session.getAttribute("name").toString());


    }

    @PostMapping(value = "/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> logout(HttpSession session) {

        session.invalidate();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path = "/deleteUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletefile(@RequestBody String filename, HttpSession session) {


        boolean x = userService.deletefile(filename,session.getAttribute("name").toString());
        return new ResponseEntity(HttpStatus.OK);

    }

    @PostMapping(path = "/sharingFile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sharefile(@RequestBody Sharewith sharewith, HttpSession session) {
        boolean x = userService.sharefile(sharewith);
        return new ResponseEntity(HttpStatus.OK);

    }

    @PostMapping(path = "/createsharefolder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createsharefolder(@RequestBody CreateShareFolder createsharefolder, HttpSession session) {

        userService.createShareFolder(createsharefolder, session.getAttribute("name").toString());
        return new ResponseEntity(HttpStatus.OK);

    }

    @PostMapping(path = "/createfolder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createfolder(@RequestBody CreateShareFolder createsharefolder, HttpSession session) {
        userService.createFolder(createsharefolder, session.getAttribute("name").toString());
        return new ResponseEntity(HttpStatus.OK);

    }


    @PostMapping("/uploadfiles")
    public ResponseEntity<?> singleFileUpload(@RequestParam("file") MultipartFile file, HttpSession session) {
       String userfolder = session.getAttribute("name").toString();
        userService.uploader(file, userfolder);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("/createGroup")
    public ResponseEntity<?> createGroup(@RequestBody String user,
                                         HttpSession session) {
        String userEmail = session.getAttribute("name").toString();
        JSONObject userObj = new JSONObject(user);


        boolean success = sharefolderservice.createGroup(userObj.getString("groupname"), userEmail);

        if (success) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addMembersToGroup")
    public ResponseEntity<?> addMembersToGroup(@RequestBody String user,
                                               HttpSession session) {
        String userEmail = session.getAttribute("name").toString();
        JSONObject userObj = new JSONObject(user);

        boolean success = sharefolderservice.addMembersToGroup(Integer.parseInt(userObj.getString("groupId")), userObj.getString("memberEmail"));

        if (success) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/listGroups")
    public ResponseEntity<?> listUserGroups(@RequestBody String user,
                                            HttpSession session) {
        String userEmail = session.getAttribute("name").toString();
        JSONObject userObj = new JSONObject(user);

        GroupT[] groupList = sharefolderservice.listUserGroups(userEmail);

        if (groupList.length > 0) {
            return new ResponseEntity(groupList, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/listMemebers")
    public ResponseEntity<?> listGroupMembers(@RequestBody String user,
                                              HttpSession session) {
        String userEmail = session.getAttribute("name").toString();
        JSONObject userObj = new JSONObject(user);


        User[] memberList = sharefolderservice.listGroupMembers(Integer.parseInt(userObj.getString("groupId")));

        if (memberList.length > 0) {
            return new ResponseEntity(memberList, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}