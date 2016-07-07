/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.validation;

import com.mycompany.televisionblog.dao.UserDao;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author apprentice
 */

public class UsernameExistsValidator implements ConstraintValidator<UsernameExists, String> {

 private Log log = LogFactory.getLog(UsernameExistsValidator.class);
 
    @Autowired
    private UserDao userDao;
 
    private UsernameExists productExists;
 

    @Override
    public boolean isValid(String target, ConstraintValidatorContext context) {
  
        try {
            
            List<String> usernames = userDao.usernameList();
            if (usernames.contains(target)) {
                return false;
            }
        } catch (Exception e) {
            log.error(e);
        }
            return true;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void initialize(UsernameExists a) {
        this.productExists = a;
    }

}
