package com.mycompany.televisionblog.dto;

import java.util.List;

/**
 *
 * @author apprentice
 */
public class Role {

    private int id;
    private String name;
    private String displayName;
    private List<Integer> userRights;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<Integer> getUserRights() {
        return userRights;
    }

    public void setUserRights(List<Integer> userRights) {
        this.userRights = userRights;
    }


    
    
    
    

}
