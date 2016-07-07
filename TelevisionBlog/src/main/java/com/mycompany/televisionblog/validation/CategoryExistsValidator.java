/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.validation;

import com.mycompany.televisionblog.dao.CategoryDao;
import com.mycompany.televisionblog.dao.UserDao;
import com.mycompany.televisionblog.dto.Category;
import java.util.ArrayList;
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

public class CategoryExistsValidator implements ConstraintValidator<CategoryExists, String> {

 private Log log = LogFactory.getLog(CategoryExistsValidator.class);
 
    @Autowired
    private CategoryDao categoryDao;
 
    private CategoryExists categoryExists;
 

    @Override
    public boolean isValid(String target, ConstraintValidatorContext context) {
  
        try {
            
            List<Category> categories = categoryDao.list();
            List<String> categoryNames = new ArrayList();
            for (Category myCategory : categories) {
                categoryNames.add(myCategory.getName());
            }
            if (categoryNames.contains(target)) {
                return false;
            }
        } catch (Exception e) {
            log.error(e);
        }
            return true;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void initialize(CategoryExists a) {
        this.categoryExists = a;
    }

}
