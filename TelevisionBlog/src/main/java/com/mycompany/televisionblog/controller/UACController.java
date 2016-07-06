/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.RoleDao;
import com.mycompany.televisionblog.dao.UserDao;
import com.mycompany.televisionblog.dao.UserRightDao;
import com.mycompany.televisionblog.dto.Role;
import com.mycompany.televisionblog.dto.User;
import com.mycompany.televisionblog.dto.UserRight;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@RequestMapping(value = "/admin/uac")
@Controller
public class UACController {

    UserDao userDao;
    RoleDao roleDao;
    UserRightDao rightsDao;

    @Inject
    public UACController(UserDao userDao, RoleDao roleDao, UserRightDao rightsDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.rightsDao = rightsDao;
    }

    @RequestMapping(value = "/blogs", method = RequestMethod.GET)
    public String blogs(@RequestParam("group") String group, Map model) {

        List<User> users = userDao.list();
        List<Role> roles = roleDao.listNoCustom();
        List<Role> customRoles = roleDao.listCustom();
        List<Integer> customRolesId = new ArrayList();
        List<UserRight> rights = rightsDao.listByGroup(group);
        
        //Add list of privledge IDs to User
        for(User u: users) {
            List<Integer> userRights = rightsDao.listRoleRightsByIdGroup(u.getGroupId(), group);
            u.setRoles(userRights);
        }
        
        for (Role r : roles) {
            List<Integer> currentRights = rightsDao.listRoleRightsByIdGroup(r.getId(), group);
            r.setUserRights(currentRights);
        }
        
        for(Role cr : customRoles) {
            customRolesId.add(cr.getUserId());
        }

        model.put("rights", rights);
        model.put("users", users);
        model.put("roles", roles);
        model.put("customRolesId", customRolesId);
        model.put("customRoles", customRoles);

        return "blogs";
    }

    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    public String pages(@RequestParam("group") String group, Map model) {

        List<User> users = userDao.list();
        List<Role> roles = roleDao.listNoCustom();
        List<Role> customRoles = roleDao.listCustom();
        List<Integer> customRolesId = new ArrayList();
        List<UserRight> rights = rightsDao.listByGroup(group);
        
        //Add list of privledge IDs to User
        for(User u: users) {
            List<Integer> userRights = rightsDao.listRoleRightsByIdGroup(u.getGroupId(), group);
            u.setRoles(userRights);
        }
        
        for (Role r : roles) {
            List<Integer> currentRights = rightsDao.listRoleRightsByIdGroup(r.getId(), group);
            r.setUserRights(currentRights);
        }
        
        for(Role cr : customRoles) {
            customRolesId.add(cr.getUserId());
        }

        model.put("rights", rights);
        model.put("users", users);
        model.put("roles", roles);
        model.put("customRolesId", customRolesId);
        model.put("customRoles", customRoles);

        return "pages";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(@RequestParam("group") String group, Map model) {

        List<User> users = userDao.list();
        List<Role> roles = roleDao.listNoCustom();
        List<Role> customRoles = roleDao.listCustom();
        List<Integer> customRolesId = new ArrayList();
        List<UserRight> rights = rightsDao.listByGroup(group);
        
        //Add list of privledge IDs to User
        for(User u: users) {
            List<Integer> userRights = rightsDao.listRoleRightsByIdGroup(u.getGroupId(), group);
            u.setRoles(userRights);
        }
        
        for (Role r : roles) {
            List<Integer> currentRights = rightsDao.listRoleRightsByIdGroup(r.getId(), group);
            r.setUserRights(currentRights);
        }
        
        for(Role cr : customRoles) {
            customRolesId.add(cr.getUserId());
        }

        model.put("rights", rights);
        model.put("users", users);
        model.put("roles", roles);
        model.put("customRolesId", customRolesId);
        model.put("customRoles", customRoles);

        return "users";
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String categories(@RequestParam("group") String group, Map model) {

        List<User> users = userDao.list();
        List<Role> roles = roleDao.listNoCustom();
        List<Role> customRoles = roleDao.listCustom();
        List<Integer> customRolesId = new ArrayList();
        List<UserRight> rights = rightsDao.listByGroup(group);
        
        //Add list of privledge IDs to User
        for(User u: users) {
            List<Integer> userRights = rightsDao.listRoleRightsByIdGroup(u.getGroupId(), group);
            u.setRoles(userRights);
        }
        
        for (Role r : roles) {
            List<Integer> currentRights = rightsDao.listRoleRightsByIdGroup(r.getId(), group);
            r.setUserRights(currentRights);
        }
        
        for(Role cr : customRoles) {
            customRolesId.add(cr.getUserId());
        }

        model.put("rights", rights);
        model.put("users", users);
        model.put("roles", roles);
        model.put("customRolesId", customRolesId);
        model.put("customRoles", customRoles);

        return "categories";
    }

    @RequestMapping(value = "/tag", method = RequestMethod.GET)
    public String tag(@RequestParam("group") String group, Map model) {

        List<User> users = userDao.list();
        List<Role> roles = roleDao.listNoCustom();
        List<Role> customRoles = roleDao.listCustom();
        List<Integer> customRolesId = new ArrayList();
        List<UserRight> rights = rightsDao.listByGroup(group);
        
        //Add list of privledge IDs to User
        for(User u: users) {
            List<Integer> userRights = rightsDao.listRoleRightsByIdGroup(u.getGroupId(), group);
            u.setRoles(userRights);
        }
        
        for (Role r : roles) {
            List<Integer> currentRights = rightsDao.listRoleRightsByIdGroup(r.getId(), group);
            r.setUserRights(currentRights);
        }
        
        for(Role cr : customRoles) {
            customRolesId.add(cr.getUserId());
        }

        model.put("rights", rights);
        model.put("users", users);
        model.put("roles", roles);
        model.put("customRolesId", customRolesId);
        model.put("customRoles", customRoles);

        return "tag";
    }

    @RequestMapping(value = "/add/{roleId}/{rightId}", method = RequestMethod.POST)
    @ResponseBody
    public void addRole(@PathVariable("roleId") Integer roleId, @PathVariable("rightId") Integer rightId, @RequestParam(name = "userId", required = false) Integer userId) {

        Role role = roleDao.get(roleId);

        if (role.getName().equals("Custom")) {
            try {
                Role oldRole = roleDao.getByUser(userId);
                rightsDao.createRoleRight(oldRole.getId(), rightId);
            } catch (EmptyResultDataAccessException e) {
                Role newRole = new Role();
                newRole.setName("Custom");
                newRole.setUserId(userId);
                Role newRoleId = roleDao.create(newRole);
                User user = userDao.get(userId);
                user.setGroupId(newRoleId.getId());
                userDao.update(user);
                userDao.removeRoles(userId);
                userDao.assignRoles(userId, newRoleId.getId());
                rightsDao.createRoleRight(newRoleId.getId(), rightId);
            }

        } else {
            rightsDao.createRoleRight(roleId, rightId);
        }

    }
    
    @RequestMapping(value="/change-role/{roleId}/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public void changeRole(@PathVariable("roleId") Integer roleId, @PathVariable("userId") Integer userId) {
        
        Role role = roleDao.get(roleId);
        
        Integer newRoleId = roleId;

        if (role.getName().equals("Custom")) {
            try {
                Role oldRole = roleDao.getByUser(userId);

            } catch (EmptyResultDataAccessException e) {
                Role newRole = new Role();
                newRole.setName("Custom");
                newRole.setUserId(userId);
                Role r = roleDao.create(newRole);
                rightsDao.createRoleRight(r.getId(), 23);
                newRoleId = r.getId();
            }

        } 
        
        User user = userDao.get(userId);
        user.setGroupId(newRoleId);
        userDao.update(user);
        userDao.removeRoles(userId);
        userDao.assignRoles(userId, user.getGroupId());
        
    }

    @RequestMapping(value = "/remove/{roleId}/{rightId}", method=RequestMethod.POST)
    @ResponseBody
    public void removeRole(@PathVariable("roleId") Integer roleId, @PathVariable("rightId") Integer rightId) {
        rightsDao.deleteRoleRight(roleId, rightId);
    }

}
