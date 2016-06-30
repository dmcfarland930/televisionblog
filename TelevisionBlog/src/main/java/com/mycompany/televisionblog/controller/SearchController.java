///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.televisionblog.controller;
//
//import com.mycompany.televisionblog.dto.Category;
//import com.mycompany.televisionblog.dto.Page;
//import java.util.List;
//import java.util.Map;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
///**
// *
// * @author apprentice
// */
//@Controller
//@RequestMapping("/search")
//public class SearchController {
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public String search(Map<String, Object> model) {
//        
//        List<Category> categories = categoryDao.list();
//        
//        List<Page> pages = pageDao.list();
//        
//        model.put("pages", pages);
//        model.put("categories", categories);
//        
//        return "categories";
//    }
//}
