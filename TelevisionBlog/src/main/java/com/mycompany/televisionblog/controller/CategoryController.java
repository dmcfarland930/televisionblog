/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.CategoryDao;
import com.mycompany.televisionblog.dao.PageDao;
import com.mycompany.televisionblog.dao.UserDao;
import com.mycompany.televisionblog.dto.Category;
import com.mycompany.televisionblog.dto.Page;
import com.mycompany.televisionblog.dto.PageCommand;
import com.mycompany.televisionblog.dto.User;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    private CategoryDao categoryDao;

    @Inject
    public CategoryController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
    
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String display(Map<String, Object> model) {
        
        List<Category> categories = categoryDao.list();
        
        model.put("categories", categories);
        
        return "categories";
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Category show(@PathVariable("id") Integer id) {
        
        Category category = categoryDao.get(id);
        return category;
    }
    
    @RequestMapping(value="", method = RequestMethod.POST)
    @ResponseBody
    public Category create(@RequestBody Category category) {
        categoryDao.create(category);
        return category;
    }
    @RequestMapping(value="", method = RequestMethod.PUT)
    @ResponseBody
    public Category update(@RequestBody Category category) {
        categoryDao.update(category);
        return category;
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer id) {
        categoryDao.delete(id);
    }
}
