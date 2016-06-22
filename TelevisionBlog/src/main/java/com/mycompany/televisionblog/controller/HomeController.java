/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.BlogPostDao;
import com.mycompany.televisionblog.dto.BlogPost;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping
public class HomeController {

    private BlogPostDao postDao;

    @Inject
    public HomeController(BlogPostDao postDao) {
        this.postDao = postDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Map<String, Object> model) {

        List<BlogPost> posts = postDao.list();

        for (BlogPost blogView : posts) {

            model.put("title", blogView.getTitle());
            model.put("date", blogView.getPostDate());
            model.put("author", blogView.getUser().getFirstName() + " " + blogView.getUser().getLastName());
            model.put("posts", posts);
        }

        return "/home";
    }
}
