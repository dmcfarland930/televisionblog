/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.Role;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface RoleDao {
    
    Role create(Role role);
    Role get(Integer id);
    void update(Role role);
    void delete(Integer id);
    
    List<Role> list();
    List<Role> getUserRoles(Integer id);
    
}
