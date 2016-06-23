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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController {
    
    BlogPostDao postDao;
    
    @Inject
    public AdminController(BlogPostDao postDao) {
        this.postDao = postDao;
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET) 
    public String home(Map model) {
        
        List<BlogPost> pendingPosts = postDao.listUnapproved();
        model.put("pendingPosts", pendingPosts);
        return "adminHome";
    }    
    
    @RequestMapping(value="/{id}", method=RequestMethod.POST)
    @ResponseBody
    public void approvePost(@PathVariable("id") Integer id) {
        
        BlogPost post = postDao.get(id);
        post.setApproved(true);
        postDao.update(post);
        
    }
    
}
