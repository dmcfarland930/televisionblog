package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.BlogPostDao;
import com.mycompany.televisionblog.dao.CategoryDao;
import com.mycompany.televisionblog.dao.UserDao;
import com.mycompany.televisionblog.dto.BlogPost;
import com.mycompany.televisionblog.dto.User;
import java.text.ParseException;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/user"})
public class UserController {

    private BlogPostDao blogPostDao;
    private UserDao userDao;
    private CategoryDao categoryDao;

    @Inject
    public UserController(BlogPostDao blogPostDao, UserDao userDao, CategoryDao categoryDao) {
        this.blogPostDao = blogPostDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String sayHi(Map<String, Object> model) {
        model.put("message", "Hello from the controller");
        return "createUser";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public User create(@RequestBody User user) {


        return userDao.create(user);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User show(@PathVariable("id") Integer id, Map model) throws ParseException {

        User user = userDao.get(id);

        return user;
    }
    
    @RequestMapping(value="/", method=RequestMethod.PUT)
    @ResponseBody
    public User edit(@RequestBody User user) {
        
        userDao.update(user);
        return user;
    };
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer id) {
        userDao.delete(id);
    }


    @RequestMapping(value = "/blogShow/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String showBlog(@PathVariable("id") Integer id, Map model) {

        BlogPost blogPost = blogPostDao.get(id);

        model.put("title", blogPost.getTitle());
        model.put("content", blogPost.getContent());
        
        return "blogShow";

    }

}