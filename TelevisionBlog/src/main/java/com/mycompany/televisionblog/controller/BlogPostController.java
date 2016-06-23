package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.BlogPostDao;
import com.mycompany.televisionblog.dao.CategoryDao;
import com.mycompany.televisionblog.dao.PageDao;
import com.mycompany.televisionblog.dao.UserDao;
import com.mycompany.televisionblog.dto.BlogPost;
import com.mycompany.televisionblog.dto.BlogPostCommand;
import com.mycompany.televisionblog.dto.Page;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/blog"})
public class BlogPostController {

    private BlogPostDao blogPostDao;
    private UserDao userDao;
    private CategoryDao categoryDao;
    private PageDao pageDao;

    @Inject
    public BlogPostController(BlogPostDao blogPostDao, UserDao userDao, CategoryDao categoryDao, PageDao pageDao) {
        this.blogPostDao = blogPostDao;
        this.userDao = userDao;
        this.pageDao = pageDao;
        this.categoryDao = categoryDao;
    }

    @RequestMapping(value = "/writeBlog", method = RequestMethod.GET)
    public String sayHi(Map model) {
        List<Page> pages = pageDao.list();

        model.put("pages", pages);

        return "writeBlog";

    }

    @RequestMapping(value = "/create-blog-post/", method = RequestMethod.POST)
    @ResponseBody
    public BlogPost create(@RequestBody BlogPostCommand blogPostCommand) {

        blogPostCommand.setUserId(1);
        BlogPost blogPost = setBlogPostProperties(blogPostCommand);

        return blogPostDao.create(blogPost);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BlogPost show(@PathVariable("id") Integer id, Map model) throws ParseException {

        BlogPost blogPost = blogPostDao.get(id);

        return blogPost;
    }

    public BlogPost setBlogPostProperties(BlogPostCommand blogPostCommand) {

        BlogPost blogPost = new BlogPost();

        blogPost.setTitle(blogPostCommand.getTitle());
        blogPost.setUser(userDao.get(blogPostCommand.getUserId()));
        blogPost.setCategory(categoryDao.get(1));
        blogPost.setContent(blogPostCommand.getContent());
        if (blogPostCommand.getPostDate() == null) {

        }
        blogPost.setPostDate(blogPostCommand.getPostDate());
        blogPost.setExpirationDate(blogPostCommand.getExpirationDate());
        blogPost.setApproved(blogPostCommand.isApproved());
        return blogPost;
    }

    @RequestMapping(value = "/blogShow/{id}", method = RequestMethod.GET)
    public String showBlog(@PathVariable("id") Integer id, Map model) {

        List<BlogPost> posts = blogPostDao.list();

        for (BlogPost blogView : posts) {

            model.put("title", blogView.getTitle());
            model.put("date", blogView.getPostDate());
            model.put("author", blogView.getUser().getFirstName() + " " + blogView.getUser().getLastName());
            model.put("posts", posts);
        }

        return "/blogShow";

    }

    @RequestMapping(value = "/page/{pageNum}", method = RequestMethod.GET)
    public String nextPage(@PathVariable("pageNum") Integer pageNum, Map model) {

        int page = pageNum + 1;
        int articles = (pageNum - 1) * 3;
        List<BlogPost> posts = blogPostDao.listOfThree(articles);
        for (BlogPost blogView : posts) {

            model.put("title", blogView.getTitle());
            model.put("date", blogView.getPostDate());
            model.put("author", blogView.getUser().getFirstName() + " " + blogView.getUser().getLastName());
            model.put("posts", posts);
        }

        List<Page> pages = pageDao.list();
        model.put("pages", pages);
        model.put("page", page);
        return "/home";

    }

}
