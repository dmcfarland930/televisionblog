/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.PageDao;
import com.mycompany.televisionblog.dao.UserDao;
import com.mycompany.televisionblog.dto.Page;
import com.mycompany.televisionblog.dto.PageCommand;
import com.mycompany.televisionblog.dto.User;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
@RequestMapping("/page")
public class PageController {

    PageDao pageDao;
    UserDao userDao;

    @Inject
    public PageController(PageDao pageDao, UserDao userDao) {
        this.pageDao = pageDao;
        this.userDao = userDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String display(Map model) {

        List<Page> pages = pageDao.list();

        model.put("pages", pages);

        return "page";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String displayCreate(Map model) {

        List<Page> pages = pageDao.list();

        model.put("pages", pages);

        return "writePage";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String displatEdit(@PathVariable("id") Integer id, Map model) {
        Page page = pageDao.get(id);

        model.put("page", page);
        return "editPage";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Page create(@Valid @RequestBody PageCommand command) {
        Page page = new Page();

        //!!!!!!!!!!!!!!!!!!!
        //DELETE ME
        command.setUserId(1);
        //DELETE ME
        //!!!!!!!!!!!!!!!!!!!

        User user = userDao.get(command.getUserId());
        page.setUser(user);

        page.setName(command.getName());
        page.setUrl(command.getUrl());
        page.setContent(command.getContent());

        try {
            return pageDao.create(page);
            
        } catch (DuplicateKeyException e) {
        
            
        }
        
        return pageDao.create(page);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public Page update(@RequestBody PageCommand command) {
        Page page = new Page();

//        User user = userDao.get(command.getUserId());
        User user = userDao.get(1);
        page.setUser(user);

        page.setName(command.getName());
        page.setUrl(command.getUrl());
        page.setContent(command.getContent());

        pageDao.update(page);

        return page;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Page get(@PathVariable("id") Integer id) {

        Page page = pageDao.get(id);
        User user = userDao.get(page.getUser().getId());

        page.setUser(user);

        return page;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer id) {
        pageDao.delete(id);
    }
    
    public String pathEx(Map model) {
        
        model.put("pathex", "A page already exists with that path-name.");
        
        return "writePage";
    }

}
