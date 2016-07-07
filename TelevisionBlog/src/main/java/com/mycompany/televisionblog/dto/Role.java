package com.mycompany.televisionblog.dto;

import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Role {

    private int id;
    
    @NotEmpty(message="You must supply a name")
    private String name;
    
    private Integer userId;
    private List<Integer> userRights;
    private List<UserRight> allUserRights;

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

    public List<Integer> getUserRights() {
        return userRights;
    }

    public void setUserRights(List<Integer> userRights) {
        this.userRights = userRights;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<UserRight> getAllUserRights() {
        return allUserRights;
    }

    public void setAllUserRights(List<UserRight> allUserRights) {
        this.allUserRights = allUserRights;
    }

    
    
}
