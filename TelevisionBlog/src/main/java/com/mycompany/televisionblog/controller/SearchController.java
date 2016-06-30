
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.BlogPostDao;
import com.mycompany.televisionblog.dao.PageDao;
import com.mycompany.televisionblog.dto.BlogPost;
import com.mycompany.televisionblog.dto.Category;
import com.mycompany.televisionblog.dto.Page;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    
    BlogPostDao blogPostDao;
    PageDao pageDao;
    SimpleDateFormat sdfDisplay = new SimpleDateFormat("MMMM dd, yyyy");
    @Inject
    public SearchController(BlogPostDao blogPostDao, PageDao pageDao) {
        this.blogPostDao = blogPostDao;
        this.pageDao = pageDao;
    }
    
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String search(Map<String, Object> model, @RequestParam("search-type") String searchType, @RequestParam("search-value") String searchValue) {
        List<BlogPost> posts = new ArrayList();
        switch(searchType) {
            case "All":
                posts = blogPostDao.listOfThreeBySearch(0, 3, searchValue);
                break;
            case "Titles":
                blogPostDao.listOfThreeBySearch(0, 3, searchValue);
                break;
            case "Posts":
                blogPostDao.listOfThreeBySearch(0, 3, searchValue);
                break;
        }
        model.put("posts", posts);
        List<String> titles = new ArrayList();
        List<String> authors = new ArrayList();
        for (BlogPost blogView : posts) {

            blogView.setStringDateDisplay(sdfDisplay.format(blogView.getPostDate()));
            titles.add(blogView.getTitle());
            authors.add(blogView.getUser().getFirstName() + " " + blogView.getUser().getLastName());

        }
        boolean nextPage = blogPostDao.checkIfNextPage(0, 3);
        List<Page> pages = pageDao.list();
        model.put("searchValue", searchValue);
        model.put("pages", pages);
        model.put("titles", titles);
        model.put("authors", authors);

        model.put("pageNext", 2);
        model.put("nextPage", nextPage);
        model.put("hidden", "hidden");
        return "searchresults";
    }
}
