/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog;

import com.mycompany.televisionblog.dao.BlogPostDao;
import com.mycompany.televisionblog.dao.PageDao;
import com.mycompany.televisionblog.dao.UserDao;
import com.mycompany.televisionblog.dto.BlogPost;
import com.mycompany.televisionblog.dto.Page;
import com.mycompany.televisionblog.dto.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class pageTest {
    
    public pageTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    ApplicationContext context = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    PageDao pageDao = (PageDao) context.getBean("pageDao");
    UserDao userDao = (UserDao) context.getBean("userDao");
    BlogPostDao postDao = (BlogPostDao) context.getBean("blogPostDao");
    
//    @Test
    public void createPageTest() {
        
        Page page = new Page();
        User user = new User(); 
        
        user.setId(1);
        
        page.setContent("some content");
        page.setName("About Me");
        page.setUrl("mikes");
        page.setUser(user);
        
        pageDao.create(page);
    };
    
    
    //Method for listing multiple pages of blogs
//    @Test
    public void something() {

        List<BlogPost> orderedPosts = postDao.list();

        List<BlogPost> posts = new ArrayList();
        Map<Integer, List<BlogPost>> pages = new HashMap();

        Integer pageNumber = 1;
        Integer counter = 0;

        for (BlogPost bp : orderedPosts) {
            counter++;
            posts.add(bp);
            if (counter % 3 == 0) {
                pages.put(pageNumber, posts);
                pageNumber++;
                posts = new ArrayList();
            }

        }

        if (!posts.isEmpty()) {
            pages.put(pageNumber, posts);
        }


    }
}
