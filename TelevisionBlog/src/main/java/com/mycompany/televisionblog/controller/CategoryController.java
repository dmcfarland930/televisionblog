/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.BlogPostDao;
import com.mycompany.televisionblog.dao.CategoryDao;
import com.mycompany.televisionblog.dao.PageDao;
import com.mycompany.televisionblog.dto.Category;
import com.mycompany.televisionblog.dto.Page;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.validation.Valid;
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
@RequestMapping("/admin/category")
public class CategoryController {

    private CategoryDao categoryDao;
    private BlogPostDao blogPostDao;
    private PageDao pageDao;
    
    @Inject
    public CategoryController(CategoryDao categoryDao, BlogPostDao blogPostDao, PageDao pageDao) {
        this.categoryDao = categoryDao;
        this.pageDao = pageDao;
        this.blogPostDao = blogPostDao;
    }
    
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String display(Map<String, Object> model) {
        
        List<Category> categories = categoryDao.list();
        
        List<Page> pages = pageDao.list();
        
        model.put("pages", pages);
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
    public Category create(@Valid @RequestBody Category category) {
        categoryDao.create(category);
        return category;
    }
    @RequestMapping(value="", method = RequestMethod.PUT)
    @ResponseBody
    public Category update(@Valid @RequestBody Category category) {
        categoryDao.update(category);
        return category;
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer id) {
        categoryDao.delete(id);
    }
    
    @RequestMapping(value="/reassign/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String reassign(@PathVariable("id") Integer id) {
        
        Integer defaultCategory = categoryDao.getDefaultCategory();
        
        blogPostDao.reassignBlogCategory(defaultCategory, id);
        
        return "/";
        
    }
}
