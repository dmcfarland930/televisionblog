
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.BlogPostDao;
import com.mycompany.televisionblog.dao.CategoryDao;
import com.mycompany.televisionblog.dao.PageDao;
import com.mycompany.televisionblog.dao.TagDao;
import com.mycompany.televisionblog.dto.BlogPost;
import com.mycompany.televisionblog.dto.CategoryPost;
import com.mycompany.televisionblog.dto.Page;
import com.mycompany.televisionblog.dto.Tag;
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
    TagDao tagDao;
    CategoryDao categoryDao;
    SimpleDateFormat sdfDisplay = new SimpleDateFormat("MMMM dd, yyyy");

    @Inject
    public SearchController(BlogPostDao blogPostDao, PageDao pageDao, TagDao tagDao, CategoryDao categoryDao) {
        this.blogPostDao = blogPostDao;
        this.pageDao = pageDao;
        this.tagDao = tagDao;
        this.categoryDao = categoryDao;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String search(Map<String, Object> model, @RequestParam("search-type") String searchType, @RequestParam("search-value") String searchValue) {
        List<BlogPost> posts = new ArrayList();
        List<BlogPost> latestPosts = blogPostDao.listOfN(0, 5);
        List<CategoryPost> categories = categoryDao.getPostCount();
        List<Tag> tags = tagDao.list();
        Map<String, Integer> months = blogPostDao.listOfPostMonths();
        switch (searchType) {
            case "All":
                posts = blogPostDao.listOfThreeBySearch(0, 3, searchValue);
                break;
            case "Titles":
                posts = blogPostDao.listOfThreeBySearchTitle(0, 3, searchValue);
                break;
            case "Posts":
                posts = blogPostDao.listOfThreeBySearchPost(0, 3, searchValue);
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
        boolean nextPage = blogPostDao.checkIfNextPageSearch(searchValue, 3, 3);
        List<Page> pages = pageDao.list();
        model.put("searchValue", searchValue);
        model.put("pages", pages);
        model.put("titles", titles);
        model.put("authors", authors);
        model.put("categories", categories);
        model.put("months", months);
        model.put("tags", tags);
        model.put("pageNext", 2);
        model.put("nextPage", nextPage);
        model.put("hidden", "hidden");
        model.put("latestPosts", latestPosts);
        return "searchresults";
    }

    @RequestMapping(value = "/{searchResult}/page/{pageNum}", method = RequestMethod.GET)
    public String nextSearchPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("searchResult") String searchResult, Map model) {
        List<BlogPost> posts = blogPostDao.listOfThreeBySearch((pageNum - 1) * 3, 3, searchResult);
        model.put("posts", posts);
        List<String> titles = new ArrayList();
        List<String> authors = new ArrayList();
        Map<String, Integer> months = blogPostDao.listOfPostMonths();
        List<CategoryPost> categories = categoryDao.getPostCount();
        List<BlogPost> latestPosts = blogPostDao.listOfN(0, 5);
        List<Tag> tags = tagDao.list();
        model.put("categories", categories);
        int pageNext = pageNum + 1;
        int pageLast = pageNum - 1;
        int articles = (pageNum - 1) * 3;
        boolean nextPage = blogPostDao.checkIfNextPageSearch(searchResult, articles + 3, 3);
        System.out.println(nextPage);

        if (posts.isEmpty()) {

            return "redirect:/404/";
        }

        for (BlogPost blogView : posts) {

            blogView.setStringDateDisplay(sdfDisplay.format(blogView.getPostDate()));
            titles.add(blogView.getTitle());
            authors.add(blogView.getUser().getFirstName() + " " + blogView.getUser().getLastName());

        }

        List<Page> pages = pageDao.list();
        if (pageNum == 1) {
            model.put("hidden", "hidden");
        }
        model.put("months", months);
        model.put("pages", pages);
        model.put("pageLast", pageLast);
        model.put("latestPosts", latestPosts);
        model.put("pageNext", pageNext);
        model.put("nextPage", nextPage);
        model.put("tags", tags);
        return "archiveBlogs";

    }
}
