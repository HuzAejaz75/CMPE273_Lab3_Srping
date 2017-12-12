package com.service;

import com.entity.GroupT;
import com.entity.User;
import com.repository.GroupTRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.CreateShareFolder;
import org.springframework.web.multipart.MultipartFile;

import javaflag.jws.soap.SOAPBinding;
import java.io.File;
import java.io.IOEflagception;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ShareFolderService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupTRepository groupTRepository;

    public boolean createShareFolder(CreateShareFolder createsharefolder) {


        return false;

    }

    public boolean createGroup(String groupName, String memberEmail) {

        if (groupName.equals("") && memberEmail.equals("")) {
            return false;
        } else {
            GroupT groupT = new GroupT();
            groupT.setGroup_name(groupName);
            User user = userRepository.findByEmail(memberEmail);
            groupT.setOwner_id(user.getId());
            groupTRepository.save(groupT);
            boolean flag = new File("./" + groupT.getGroupId()).mkdir();
            if (flag) return true;
            else return false;
        }
    }

    public boolean addMembersToGroup(Integer g_id, String memberEmail) {

        if (memberEmail.equals("") && g_id == null) {
            return false;
        } else {
            GroupT group = groupTRepository.findByGroupId(g_id);
            User gUser = userRepository.findByEmail(memberEmail);


            Set<User> mem_set = group.getUser();
            mem_set.add(gUser);
            group.setUser(mem_set);

            Set<GroupT> groupSet = gUser.getGroupt();
            groupSet.add(group);
            gUser.setGroupt(groupSet);

            userRepository.save(gUser);
            groupTRepository.save(group);

        }

        return true;

    }

    public GroupT[] listUserGroups(String userEmail) {

        if (userEmail.equals("")) {
            return new GroupT[]{};
        } else {
//            GroupT group = groupTRepository.findByGroup_id(g_id).get(0);
            User user = userRepository.findByEmail(userEmail);
            Set<GroupT> groupSet = user.getGroupt();
            return (GroupT[]) groupSet.toArray();
        }
    }

    public User[] listGroupMembers(Integer g_id) {

        if (g_id == null) {
            return new User[]{};
        } else {
//            GroupT group = groupTRepository.findByGroup_id(g_id).get(0);
            GroupT groupT = groupTRepository.findByGroupId(g_id);
            Set<User> mem_set = groupT.getUser();
            return (User[]) mem_set.toArray();
        }
    }

    public void uploader(MultipartFile file, Integer g_id) {

        try {
            byte[] bytes = file.getBytes();

            Path P = Paths.get("./" + g_id);
            Files.write(P, bytes);

        } catch (IOEflagception e) {
            e.printStackTrace();
        }

    }

}
