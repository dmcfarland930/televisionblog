/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.UserRight;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface UserRightDao {
    
    UserRight create(UserRight userRight);
    UserRight get(Integer id);
    void update(UserRight userRight);
    void delete(Integer id);
    
    List<UserRight> list();
    
    void createRoleRight(Integer roleId, Integer rightId);
    List<UserRight> listRoleRights(Integer id);
    List<Integer> listRoleRightsByIdGroup(Integer id, String group);
    void deleteRoleRight(Integer roleId, Integer rightId);
    
    List<UserRight> listByGroup(String group);
    
}
