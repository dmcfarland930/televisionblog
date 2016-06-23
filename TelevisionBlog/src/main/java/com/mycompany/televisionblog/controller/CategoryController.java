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
    private PageDao pageDao;
    
    @Inject
    public CategoryController(CategoryDao categoryDao, PageDao pageDao) {
        this.categoryDao = categoryDao;
        this.pageDao = pageDao;
    }
    
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String display(Map<String, Object> model) {
        
        List<Category> categories = categoryDao.list();
        
        List<Page> pages = pageDao.list();
        
        model.put("pages", pages);
        model.put("categories", categories);
        
        return "categories";
    }
    
    @RequestMapping(value="", method = RequestMethod.POST)
    @ResponseBody
    public Category create(@RequestBody Category category) {
        categoryDao.create(category);
        return category;
    }
}
