package com.mycompany.televisionblog.dto;

import com.mycompany.televisionblog.validation.UsernameExists;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class User {

    private int id;
    private int groupId;
    @NotNull
    @NotEmpty(message="You must enter a first name!")
    private String firstName;
    @NotNull
    @NotEmpty(message="You must enter a last name!")
    private String lastName;
    @UsernameExists
    @NotNull
    @NotEmpty(message="You must enter a username!")
    private String username;
    @NotNull
    @NotEmpty(message="You must enter a password!")
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
