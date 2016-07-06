/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.RoleDao;
import com.mycompany.televisionblog.dto.Role;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/admin/role")
public class RoleController {
    
    RoleDao roleDao;
    
    @Inject
    public RoleController(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    
    @RequestMapping(value="/create/", method=RequestMethod.POST)
    @ResponseBody
    public Role create(@RequestBody Role role) {
        return roleDao.create(role);      
    }
    
    @RequestMapping(value="/update/", method=RequestMethod.PUT)
    @ResponseBody
    public Role update(@RequestBody Role role)  {
        roleDao.update(role);
        return role;
    }
    
    @RequestMapping(value="/get/{id}", method=RequestMethod.GET)
    @ResponseBody
    public Role get(@PathVariable("id") Integer roleId) {
        return roleDao.get(roleId);
    }
    
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer roleId) {
        roleDao.delete(roleId);
    }
}
