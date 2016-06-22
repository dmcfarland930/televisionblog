/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.televisionblog;

import com.mycompany.televisionblog.dao.BlogPostDao;
import com.mycompany.televisionblog.dao.CategoryDao;
import com.mycompany.televisionblog.dao.UserDao;
import com.mycompany.televisionblog.dto.BlogPost;
import com.mycompany.televisionblog.dto.Category;
import com.mycompany.televisionblog.dto.User;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class blogPostTests {

    ApplicationContext context = new ClassPathXmlApplicationContext("test-applicationContext.xml");

    private BlogPostDao blogPostDao = (BlogPostDao) context.getBean("blogPostDao");
    private CategoryDao categoryDao = (CategoryDao) context.getBean("categoryDao");
    private UserDao userDao = (UserDao) context.getBean("userDao");

    public blogPostTests() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createBlogPost() {

        User user;

        Date postDate = new Date();
        Date expirationDate = new Date();

        Category category = new Category();
        BlogPost blogPost = new BlogPost();

        user = userDao.get(1);

//        category.setCategoryName("Testing");

        categoryDao.create(category);
        category = categoryDao.get(1);

        blogPost.setTitle("TEST POST");
        blogPost.setContent("THIS IS A POST FOR MY BLOG");
        blogPost.setCategory(category);
        blogPost.setUser(user);
        blogPost.setPostDate(postDate);
        blogPost.setExpirationDate(expirationDate);
        blogPost.setApproved(true);

        blogPostDao.create(blogPost);
              
        blogPostDao.delete(blogPost.getId());
        categoryDao.delete(category.getId());

    }
}
