/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.PageDao;
import com.mycompany.televisionblog.dto.Page;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author apprentice
 */
@Controller
public class LoginController {
    
    PageDao pageDao;
    
    @Inject
    public LoginController(PageDao pageDao) {
        this.pageDao = pageDao;
    }

    @RequestMapping(value = "/authenticate/login", method = RequestMethod.GET)
    public String login(@RequestParam(name = "login_error", required = false) Integer loginError, Map model) {
        
        List<Page> pages = pageDao.list();
        
        model.put("loginError", loginError);
        model.put("pages", pages);
        return "login";
    }

}
