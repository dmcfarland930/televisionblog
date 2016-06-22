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
public class userTests {

    ApplicationContext context = new ClassPathXmlApplicationContext("test-applicationContext.xml");

    private UserDao userDao = (UserDao) context.getBean("userDao");

    public userTests() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createBlogPost() {

        User user = new User();

        user.setFirstName("Dan");
        user.setLastName("McFarland");
        user.setUsername("dMcFarland");
        user.setPassword("dan930");
        user.setGroupId(1);


        userDao.create(user);

        userDao.delete(user.getId());

    }
}
