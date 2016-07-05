/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.PageDao;
import com.mycompany.televisionblog.dao.TagDao;
import com.mycompany.televisionblog.dto.Page;
import com.mycompany.televisionblog.dto.Tag;
import java.util.ArrayList;
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
@RequestMapping("/admin/tag")
public class TagController {
    private TagDao tagDao;
    private PageDao pageDao;
    
    @Inject
    public TagController(TagDao tagDao, PageDao pageDao) {
        this.tagDao = tagDao;
        this.pageDao = pageDao;
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String display(Map<String, Object> model) {
        
        List<Tag> tags = tagDao.list();
        List<Integer> counts = new ArrayList();
        for (Tag myTag : tags) {
            counts.add(tagDao.tagPostCount(myTag.getName()));
        }
        List<Page> pages = pageDao.list();
        
        model.put("pages", pages);
        model.put("tags", tags);
        model.put("counts", counts);
        
        return "tags";
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Tag show(@PathVariable("id") Integer id) {
        
        Tag tag = tagDao.get(id);
        return tag;
    }
    
    @RequestMapping(value="/create/", method = RequestMethod.POST)
    @ResponseBody
    public Tag create(@Valid @RequestBody Tag tag) {
        tagDao.create(tag);
        return tag;
    }
    @RequestMapping(value="/update/", method = RequestMethod.PUT)
    @ResponseBody
    public Tag update(@Valid @RequestBody Tag tag) {
        tagDao.update(tag);
        return tag;
    }
    
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer id) {
        tagDao.delete(id);
    }
}
