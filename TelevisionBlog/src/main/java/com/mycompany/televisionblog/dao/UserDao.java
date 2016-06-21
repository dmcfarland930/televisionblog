/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.User;

/**
 *
 * @author apprentice
 */
public interface UserDao {
    
    User create(User user);
    User get(Integer id);
    void update(User user);    
    void delete(Integer id);
    
}
