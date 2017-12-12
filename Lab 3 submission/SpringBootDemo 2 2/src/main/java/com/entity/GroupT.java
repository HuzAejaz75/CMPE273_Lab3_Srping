package com.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity // This tells Hibernate to make a table out of this class
public class GroupT {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer grpID;

    private String grpName; //folder name

    private Integer ownId;

    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "groupt")
    private Set<User> user;

    public String getgrpName() {
        return grpName;
    }

    public void setgrpName(String grpName) {
        this.grpName = grpName;
    }

    public Integer getownId() {
        return ownId;
    }

    public void setownId(Integer ownId) {
        this.ownId = ownId;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Integer getgrpID() {
        return grpID;
    }

    public void setgrpID(Integer grpID) {
        this.grpID = grpID;
    }


}
