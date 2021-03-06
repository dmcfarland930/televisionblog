package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.BlogPostDao;
import com.mycompany.televisionblog.dao.CategoryDao;
import com.mycompany.televisionblog.dao.RoleDao;
import com.mycompany.televisionblog.dao.UserDao;
import com.mycompany.televisionblog.dao.UserRightDao;
import com.mycompany.televisionblog.dto.BlogPost;
import com.mycompany.televisionblog.dto.Role;
import com.mycompany.televisionblog.dto.User;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/admin/user"})
public class UserController {

    private BlogPostDao blogPostDao;
    private UserDao userDao;
    private CategoryDao categoryDao;
    private RoleDao roleDao;
    private UserRightDao rightDao;

    @Inject
    public UserController(BlogPostDao blogPostDao, UserDao userDao, CategoryDao categoryDao, RoleDao roleDao, UserRightDao rightDao) {
        this.blogPostDao = blogPostDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
        this.roleDao = roleDao;
        this.rightDao = rightDao;
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String sayHi(Map<String, Object> model) {
        model.put("message", "Hello from the controller");
        return "createUser";
    }

    @RequestMapping(value = "/create/", method = RequestMethod.POST)
    @ResponseBody
    public User create(@Valid @RequestBody User user) {

        user = userDao.create(user);

        try {
            Role r = roleDao.get(user.getGroupId());
            userDao.assignRoles(user.getId(), r.getId());

        } catch (EmptyResultDataAccessException e) {
            Role newRole = new Role();
            newRole.setName("Custom");
            newRole.setUserId(user.getId());
            Role newRoleId = roleDao.create(newRole);
            user.setGroupId(newRoleId.getId());
            userDao.update(user);
            userDao.assignRoles(user.getId(), newRoleId.getId());
            rightDao.createRoleRight(newRoleId.getId(), 23);
        }

        return user;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User show(@PathVariable("id") Integer id, Map model) throws ParseException {

        User user = userDao.get(id);

        return user;
    }

    @RequestMapping(value = "/update/", method = RequestMethod.PUT)
    @ResponseBody
    public User edit(@Valid @RequestBody User user) {

        userDao.update(user);

        userDao.removeRoles(user.getId());

        List<Role> userRoles = roleDao.getUserRoles(user.getGroupId());

        for (Role r : userRoles) {
            userDao.assignRoles(user.getId(), r.getId());
        }

        return user;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer id) {

        try {
            Role role = roleDao.getByUser(id);
            rightDao.deleteByRole(role.getId());
        } catch (EmptyResultDataAccessException e) {

        }


        userDao.removeRoles(id);

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
