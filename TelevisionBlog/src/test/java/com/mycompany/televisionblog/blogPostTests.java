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
import junit.framework.Assert;
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
        Category category;

        Date postDate = new Date();
        Date expirationDate = new Date();

        BlogPost blogPost = new BlogPost();

        user = userDao.get(1);

        category = categoryDao.get(1);

        blogPost.setTitle("TEST POST");
        blogPost.setContent("THIS IS A POST FOR MY BLOG");
        blogPost.setCategory(category);
        blogPost.setUser(user);
        blogPost.setPostDate(postDate);
        blogPost.setExpirationDate(expirationDate);
        blogPost.setApproved(true);

        blogPostDao.create(blogPost);

        BlogPost test = blogPostDao.get(blogPost.getId());

        Assert.assertEquals("TEST POST", test.getTitle());
        Assert.assertEquals("THIS IS A POST FOR MY BLOG", test.getContent());

        blogPostDao.delete(blogPost.getId());

    }

    @Test
    public void update() {

        User user;
        Category category;

        Date postDate = new Date();
        Date expirationDate = new Date();

        BlogPost blogPost = new BlogPost();

        user = userDao.get(1);

        category = categoryDao.get(1);

        blogPost.setTitle("TEST POST");
        blogPost.setContent("THIS IS A POST FOR MY BLOG");
        blogPost.setCategory(category);
        blogPost.setUser(user);
        blogPost.setPostDate(postDate);
        blogPost.setExpirationDate(expirationDate);
        blogPost.setApproved(true);

        blogPostDao.create(blogPost);

        Assert.assertEquals("TEST POST", blogPost.getTitle());
        blogPost.setTitle("UPDATED POST");


        blogPostDao.update(blogPost);

        BlogPost test = blogPostDao.get(blogPost.getId());

        Assert.assertEquals("UPDATED POST", test.getTitle());
        Assert.assertEquals("THIS IS A POST FOR MY BLOG", test.getContent());

        blogPostDao.delete(blogPost.getId());

    }

}
