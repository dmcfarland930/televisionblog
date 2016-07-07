package com.mycompany.televisionblog.dto;

import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class User {

    private int id;
    private int groupId;
    @NotEmpty(message="You must supply a First Name")
    private String firstName;
    @NotEmpty(message="You must supply a Last Name")
    private String lastName;
    @NotEmpty(message="You must supply a username")
    private String username;
    @NotEmpty(message="You must supply a Password")
    private String password;
    private List<Integer> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }
    
    

}
