/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.User;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface UserDao {

    User create(User user);

    User get(Integer id);

    void update(User user);

    void delete(Integer id);
    
    void assignRoles(Integer userId, Integer roleId);
    
    void removeRoles(Integer userId);

    List <User> list();
    
    List<String> usernameList();
}
