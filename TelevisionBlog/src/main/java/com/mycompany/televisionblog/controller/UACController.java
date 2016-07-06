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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String blogs(Map model) {

        List<User> users = userDao.list();
        List<Role> roles = roleDao.list();
        List<UserRight> rights = rightsDao.listByGroup("POST");
        for (Role r : roles) {
            List<Integer> currentRights = rightsDao.listRoleRightsByIdGroup(r.getId(), "POST");
            r.setUserRights(currentRights);
        }

        model.put("rights", rights);
        model.put("users", users);
        model.put("roles", roles);

        return "blogs";
    }

    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    public String pages(Map model) {

        List<User> users = userDao.list();
        List<Role> roles = roleDao.list();
        List<UserRight> rights = rightsDao.listByGroup("PAGE");
        for (Role r : roles) {
            List<Integer> currentRights = rightsDao.listRoleRightsByIdGroup(r.getId(), "PAGE");
            r.setUserRights(currentRights);
        }

        model.put("rights", rights);
        model.put("users", users);
        model.put("roles", roles);

        return "pages";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Map model) {

        List<User> users = userDao.list();
        List<Role> roles = roleDao.list();
        List<UserRight> rights = rightsDao.listByGroup("USER");
        for (Role r : roles) {
            List<Integer> currentRights = rightsDao.listRoleRightsByIdGroup(r.getId(), "USER");
            r.setUserRights(currentRights);
        }

        model.put("rights", rights);
        model.put("users", users);
        model.put("roles", roles);

        return "users";
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String categories(Map model) {

        List<User> users = userDao.list();
        List<Role> roles = roleDao.list();
        List<UserRight> rights = rightsDao.listByGroup("CATEGORY");
        for (Role r : roles) {
            List<Integer> currentRights = rightsDao.listRoleRightsByIdGroup(r.getId(), "CATEGORY");
            r.setUserRights(currentRights);
        }

        model.put("rights", rights);
        model.put("users", users);
        model.put("roles", roles);

        return "categories";
    }

    @RequestMapping(value = "/tag", method = RequestMethod.GET)
    public String tag(Map model) {

        List<User> users = userDao.list();
        List<Role> roles = roleDao.list();
        List<UserRight> rights = rightsDao.listByGroup("TAG");
        for (Role r : roles) {
            List<Integer> currentRights = rightsDao.listRoleRightsByIdGroup(r.getId(), "TAG");
            r.setUserRights(currentRights);
        }

        model.put("rights", rights);
        model.put("users", users);
        model.put("roles", roles);

        return "tag";
    }

    @RequestMapping(value = "/add/{roleId}/{rightId}", method = RequestMethod.GET)
    @ResponseBody
    public void addRole(@PathVariable("roleId") Integer roleId, @PathVariable("rightId") Integer rightId) {
        rightsDao.createRoleRight(roleId, rightId);
    }

    @RequestMapping(value = "/remove/{roleId}/{rightId}")
    @ResponseBody
    public void removeRole(@PathVariable("roleId") Integer roleId, @PathVariable("rightId") Integer rightId) {
        rightsDao.deleteRoleRight(roleId, rightId);
    }

}
