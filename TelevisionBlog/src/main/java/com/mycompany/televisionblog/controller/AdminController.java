/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.BlogPostDao;
import com.mycompany.televisionblog.dao.CategoryDao;
import com.mycompany.televisionblog.dao.PageDao;
import com.mycompany.televisionblog.dao.RoleDao;
import com.mycompany.televisionblog.dao.TagDao;
import com.mycompany.televisionblog.dao.UserDao;
import com.mycompany.televisionblog.dao.UserRightDao;
import com.mycompany.televisionblog.dto.BlogPost;
import com.mycompany.televisionblog.dto.Category;
import com.mycompany.televisionblog.dto.CategoryPost;
import com.mycompany.televisionblog.dto.Page;
import com.mycompany.televisionblog.dto.Role;
import com.mycompany.televisionblog.dto.Tag;
import com.mycompany.televisionblog.dto.User;
import com.mycompany.televisionblog.dto.UserRight;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    BlogPostDao postDao;
    PageDao pageDao;
    UserDao userDao;
    CategoryDao categoryDao;
    RoleDao roleDao;
    TagDao tagDao;
    UserRightDao rightsDao;

    @Inject
    public AdminController(BlogPostDao postDao, PageDao pageDao, UserDao userDao, CategoryDao categoryDao, RoleDao roleDao, TagDao tagDao, UserRightDao rightsDao) {
        this.postDao = postDao;
        this.pageDao = pageDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
        this.roleDao = roleDao;
        this.tagDao = tagDao;
        this.rightsDao = rightsDao;
                

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Map model) {
        
        List<BlogPost> blogs = postDao.list();
        List<BlogPost> blogsApproved = postDao.listApproved();
        List<BlogPost> blogsUnapproved = postDao.listUnapproved();
        
        List<User> users = userDao.list();
        List<Category> categories = categoryDao.list();
        List<Page> pages = pageDao.list();
        List<Tag> tags = tagDao.list();
        
        model.put("numBlogs", blogs.size());
        model.put("numBlogsApproved", blogsApproved.size());
        model.put("numBlogsNeedApproved", blogsUnapproved.size());
        model.put("numUsers", users.size());
        model.put("numCategories", categories.size());
        model.put("numTags", tags.size());
        
        
        
        return "adminHome";
    }

    @RequestMapping(value = "/pending", method = RequestMethod.GET)
    public String pendingPosts(Map model) {

        List<BlogPost> pendingPosts = postDao.listUnapproved();
        List<BlogPost> expired = new ArrayList();
        List<BlogPost> active = new ArrayList();
        for (BlogPost bp : pendingPosts) {
            if (bp.isActive()) {
                active.add(bp);
            } else {
                expired.add(bp);
            }
        }
        model.put("expired", expired);
        model.put("active", active);
        return "pendingPost";
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String blogPosts(Map model) {

        List<BlogPost> posts = postDao.listApproved();
        model.put("posts", posts);
        return "post";
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String staticPages(Map model) {

        List<Page> pages = pageDao.list();
        model.put("pages", pages);
        return "page";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String users(Map model) {

        List<User> users = userDao.list();
        List<Role> roles = roleDao.listNoCustom();

        model.put("roles", roles);
        model.put("users", users);
        return "user";
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String categories(Map model) {

        List<CategoryPost> categories = categoryDao.getPostCount();
        model.put("categories", categories);

        return "category";
    }

    @RequestMapping(value = "/uac", method = RequestMethod.GET)
    public String uac(Map model) {

        List<Role> roles = roleDao.listNoCustom();
        List<User> users = userDao.list();
        
        for(Role r : roles) {
            List<UserRight> rights = rightsDao.listRoleRights(r.getId());
            r.setAllUserRights(rights);
        }

        model.put("allRights", roles.get(0).getAllUserRights());
        model.put("users", users);
        model.put("roles", roles);

        return "uac";
    }

    @RequestMapping(value = "/approval/{val}/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean approvePost(@PathVariable("id") Integer id, @PathVariable("val") Integer val) {

        BlogPost post = postDao.get(id);
        Boolean approve = true;

        if (val == 1) {
            if (post.isActive()) {
                post.setApproved(true);
            } else {
                approve = false;
            }
        } else if (val == 2) {
            post.setApproved(false);

        }

        postDao.update(post);
        return approve;

    }

}
