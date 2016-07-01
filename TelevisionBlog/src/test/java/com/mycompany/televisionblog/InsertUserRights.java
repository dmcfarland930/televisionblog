/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog;

import com.mycompany.televisionblog.dao.UserRightDao;
import com.mycompany.televisionblog.dto.UserRight;
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
public class InsertUserRights {

    public InsertUserRights() {
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
    UserRightDao rightsDao = (UserRightDao) context.getBean("userRightDao");

    public void InsertUserRightsAll() {

        UserRight right = new UserRight();
        right.setId(1);
        right.setName("ROLE_CREATE_POST");
        right.setGroupName("POST");
        rightsDao.create(right);
        
        right.setId(2);
        right.setName("ROLE_READ_POST");
        right.setGroupName("POST");
        rightsDao.create(right);
        
        right.setId(3);
        right.setName("ROLE_UPDATE_POST");
        right.setGroupName("POST");
        rightsDao.create(right);
        
        right.setId(4);
        right.setName("ROLE_DELETE_POST");
        right.setGroupName("POST");
        rightsDao.create(right);

        right.setId(5);
        right.setName("ROLE_CREATE_PAGE");
        right.setGroupName("PAGE");
        rightsDao.create(right);
        
        right.setId(6);
        right.setName("ROLE_READ_PAGE");
        right.setGroupName("PAGE");
        rightsDao.create(right);
        
        right.setId(7);
        right.setName("ROLE_UPDATE_PAGE");
        right.setGroupName("PAGE");
        rightsDao.create(right);
        
        right.setId(8);
        right.setName("ROLE_DELETE_PAGE");
        right.setGroupName("PAGE");
        rightsDao.create(right);
        
        right.setId(9);
        right.setName("ROLE_CREATE_USER");
        right.setGroupName("USER");
        rightsDao.create(right);
        
        right.setId(10);
        right.setName("ROLE_READ_USER");
        right.setGroupName("USER");
        rightsDao.create(right);
        
        right.setId(11);
        right.setName("ROLE_UPDATE_USER");
        right.setGroupName("USER");
        rightsDao.create(right);
        
        right.setId(12);
        right.setName("ROLE_DELETE_USER");
        right.setGroupName("USER");
        rightsDao.create(right);
        
        right.setId(13);
        right.setName("ROLE_CREATE_CATEGORY");
        right.setGroupName("CATEGORY");
        rightsDao.create(right);
        
        right.setId(14);
        right.setName("ROLE_READ_CATEGORY");
        right.setGroupName("CATEGORY");
        rightsDao.create(right);
        
        right.setId(15);
        right.setName("ROLE_UPDATE_CATEGORY");
        right.setGroupName("CATEGORY");
        rightsDao.create(right);
        
        right.setId(16);
        right.setName("ROLE_DELETE_CATEGORY");
        right.setGroupName("CATEGORY");
        rightsDao.create(right);
        
        right.setId(17);
        right.setName("ROLE_CREATE_TAG");
        right.setGroupName("TAG");
        rightsDao.create(right);
        
        right.setId(18);
        right.setName("ROLE_READ_TAG");
        right.setGroupName("TAG");
        rightsDao.create(right);
        
        right.setId(19);
        right.setName("ROLE_UPDATE_TAG");
        right.setGroupName("TAG");
        rightsDao.create(right);
        
        right.setId(20);
        right.setName("ROLE_DELETE_TAG");
        right.setGroupName("TAG");
        rightsDao.create(right);
        
        right.setId(21);
        right.setName("ROLE_ADMIN");
        right.setGroupName("ADMIN");
        rightsDao.create(right);
        
        right.setId(22);
        right.setName("ROLE_MARKETING");
        right.setGroupName("MARKETING");
        rightsDao.create(right);
    }
}
