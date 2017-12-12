package com.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String fName;
    

    private String lName;

    private String email;

    private String password;
    
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "Group_User", joinColumns = {@JoinColumn( name = "user_id") }, inverseJoinColumns = {@JoinColumn( name = "group_id") })
	private Set<GroupT> groupt;


	public Set<GroupT> getGroupt() {
		return groupt;
	}

	public void setGroupt(Set<GroupT> groupt) {
		this.groupt = groupt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}