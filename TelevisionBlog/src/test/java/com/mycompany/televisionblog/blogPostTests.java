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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
    public void createBlogPost() throws UnsupportedEncodingException {

        User user;
        Category category;

        Date postDate = new Date();
        Date expirationDate = new Date();

        BlogPost blogPost = new BlogPost();

        user = userDao.get(13);

        category = categoryDao.get(3);

        blogPost.setTitle("TEST POST");
        blogPost.setUrl(URLEncoder.encode(blogPost.getTitle(), "UTF-8"));
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

        blogPostDao.delete(blogPost);

    }

    @Test
    public void update() throws UnsupportedEncodingException {

        User user;
        Category category;

        Date postDate = new Date();
        Date expirationDate = new Date();

        BlogPost blogPost = new BlogPost();

        user = userDao.get(13);

        category = categoryDao.get(3);

        blogPost.setTitle("TEST POST");
        blogPost.setUrl(URLEncoder.encode(blogPost.getTitle(), "UTF-8"));
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

        blogPostDao.delete(blogPost);

    }

    public void checkExpiration() {

        Date date = new Date("11-29-2999");

    }

    @Test
    public void checkDuplicatePost() throws UnsupportedEncodingException {

        User user;
        Category category;

        Date postDate = new Date();
        Date expirationDate = new Date();

        BlogPost blogPost = new BlogPost();
        BlogPost blogPost2 = new BlogPost();

        user = userDao.get(13);

        category = categoryDao.get(3);

        blogPost.setTitle("TEST POST");
        blogPost.setContent("THIS IS A POST FOR MY BLOG");
        blogPost.setCategory(category);
        blogPost.setUser(user);
        blogPost.setUrl(URLEncoder.encode(blogPost.getTitle(), "UTF-8"));
        blogPost.setPostDate(postDate);
        blogPost.setExpirationDate(expirationDate);
        blogPost.setApproved(true);

        blogPost2.setTitle("TEST POST");
        blogPost2.setUrl(URLEncoder.encode(blogPost.getTitle(), "UTF-8"));

        blogPost2.setContent("THIS IS A POST FOR MY BLOG");
        blogPost2.setCategory(category);
        blogPost2.setUser(user);
        blogPost2.setPostDate(postDate);
        blogPost2.setExpirationDate(expirationDate);
        blogPost2.setApproved(true);

        blogPostDao.create(blogPost);
        blogPostDao.create(blogPost2);

        Assert.assertEquals(blogPost.getUrl() + "+" + 1, blogPost.getUrl() + "+" + 1);

        blogPostDao.delete(blogPost);
        blogPostDao.delete(blogPost2);

    }

    @Test
    public void reassignCategory() throws UnsupportedEncodingException {

        Category testCat = new Category();
        
        testCat.setName("test");
        
        categoryDao.create(testCat);
        
        User user;

        Date postDate = new Date();
        Date expirationDate = new Date();

        BlogPost blogPost = new BlogPost();

        user = userDao.get(13);

        blogPost.setTitle("TEST POST");
        blogPost.setUrl(URLEncoder.encode(blogPost.getTitle(), "UTF-8"));
        blogPost.setContent("THIS IS A POST FOR MY BLOG");
        blogPost.setCategory(testCat);
        blogPost.setUser(user);
        blogPost.setPostDate(postDate);
        blogPost.setExpirationDate(expirationDate);
        blogPost.setApproved(true);

        blogPostDao.create(blogPost);
        
        
        Assert.assertEquals("test", blogPost.getCategory().getName());
        
        blogPostDao.reassignBlogCategory(categoryDao.getDefaultCategory(), testCat.getId());
        
        blogPost = blogPostDao.get(blogPost.getId());
        
        int defaultId = categoryDao.getDefaultCategory();
        
        Assert.assertEquals(defaultId, blogPost.getCategory().getId());
        
        categoryDao.delete(testCat.getId());
        blogPostDao.create(blogPost);
        
        
    }

}
