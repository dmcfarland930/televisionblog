package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.BlogPostDao;
import com.mycompany.televisionblog.dao.CategoryDao;
import com.mycompany.televisionblog.dao.FileUploadDao;
import com.mycompany.televisionblog.dao.PageDao;
import com.mycompany.televisionblog.dao.UserDao;
import com.mycompany.televisionblog.dto.BlogPost;
import com.mycompany.televisionblog.dto.BlogPostCommand;
import com.mycompany.televisionblog.dto.Category;
import com.mycompany.televisionblog.dto.Page;
import com.mycompany.televisionblog.dto.UploadedFile;
import com.mycompany.televisionblog.dto.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/blog"})
public class BlogPostController {

    SimpleDateFormat sdfDisplay = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss a");
    SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private BlogPostDao blogPostDao;
    private UserDao userDao;
    private CategoryDao categoryDao;
    private PageDao pageDao;
    private FileUploadDao fileUploadDao;
    
    @Inject
    public BlogPostController(BlogPostDao blogPostDao, UserDao userDao, CategoryDao categoryDao, PageDao pageDao, FileUploadDao fileUploadDao) {
        this.blogPostDao = blogPostDao;
        this.userDao = userDao;
        this.pageDao = pageDao;
        this.categoryDao = categoryDao;
        this.fileUploadDao = fileUploadDao;
    }

    @RequestMapping(value = "/writeBlog", method = RequestMethod.GET)
    public String writeBlogPost(Map model) {
        
        List<Page> pages = pageDao.list();
        model.put("pages", pages);
        List<Category> categories = categoryDao.list();
        model.put("categories", categories);
        List<UploadedFile> fileList = fileUploadDao.list();
            List<Integer> idList = new ArrayList<>();
            for (UploadedFile uf : fileList) {
                idList.add(uf.getId());
            }
        model.put("idList", idList);
        return "writeBlog";
    }

    @RequestMapping(value = "/{postName}", method = RequestMethod.GET)
    public String showBlog(@PathVariable("postName") String postName, Map model) {

        BlogPost post = blogPostDao.get(postName);

        model.put("title", post.getTitle());
        model.put("date", post.getPostDate());
        model.put("author", post.getUser().getFirstName() + " " + post.getUser().getLastName());
        model.put("content", post.getContent());

        List<Page> pages = pageDao.list();
        model.put("pages", pages);
        return "/blogShow";

    }

    @RequestMapping(value = "/create-blog-post/", method = RequestMethod.POST)
    @ResponseBody
    public BlogPost create(@RequestBody BlogPostCommand blogPostCommand) {

        blogPostCommand.setUserId(1);
        BlogPost blogPost = setBlogPostProperties(blogPostCommand);

        return blogPostDao.create(blogPost);

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPost(@PathVariable("id") Integer id, Map model) {

        BlogPost blogEdit = blogPostDao.get(id);
        List<Category> categories = categoryDao.list();
        List<User> authors = userDao.list();

        model.put("id", blogEdit.getId());
        model.put("date", blogEdit.getPostDate());
        model.put("title", blogEdit.getTitle());
        model.put("categories", categories);
        model.put("category", blogEdit.getCategory());
        model.put("authors", authors);
        model.put("author", blogEdit.getUser().getFirstName() + " " + blogEdit.getUser().getLastName());
        model.put("content", blogEdit.getContent());

        List<Page> pages = pageDao.list();
        model.put("pages", pages);

        return "/editBlog";

    }

    @RequestMapping(value = "/editsubmit/", method = RequestMethod.POST)
    public String editPostSubmit(@RequestParam("id") Integer id, @RequestParam("date") String date, @RequestParam("title") String title, @RequestParam("author") Integer author,
            @RequestParam("category") Integer category, @RequestParam("content") String content, Map model) throws ParseException {

        Date postDate = sdfSQL.parse(date);

        BlogPost blogEdit = new BlogPost();
        blogEdit.setId(id);
        blogEdit.setTitle(title);
        blogEdit.setUser(userDao.get(author));
        blogEdit.setCategory(categoryDao.get(category));
        blogEdit.setContent(content);
        blogEdit.setPostDate(postDate);
        blogEdit.setApproved(true);

        blogPostDao.update(blogEdit);

        return "redirect:/admin/";

    }

    public BlogPost setBlogPostProperties(BlogPostCommand blogPostCommand) {

        BlogPost blogPost = new BlogPost();

        blogPost.setTitle(blogPostCommand.getTitle());
        blogPost.setUser(userDao.get(blogPostCommand.getUserId()));
        blogPost.setCategory(categoryDao.get(1));
        blogPost.setContent(blogPostCommand.getContent());
        blogPost.setPostDate(blogPostCommand.getPostDate());
        blogPost.setStringDateDisplay(sdfDisplay.format(blogPostCommand.getPostDate()));
        blogPost.setExpirationDate(blogPostCommand.getExpirationDate());
        blogPost.setApproved(blogPostCommand.isApproved());
        return blogPost;
    }

    @RequestMapping(value = "/blogShow/{id}", method = RequestMethod.GET)
    public String showBlog(@PathVariable("id") Integer id, Map model) {

        List<BlogPost> posts = blogPostDao.list();

        for (BlogPost blogView : posts) {

            blogView.setStringDateDisplay(sdfDisplay.format(blogView.getPostDate()));
            model.put("title", blogView.getTitle());
            model.put("date", blogView.getPostDate());
            model.put("author", blogView.getUser().getFirstName() + " " + blogView.getUser().getLastName());
            model.put("posts", posts);
        }

        return "/blogShow";

    }

    @RequestMapping(value = "/page/{pageNum}", method = RequestMethod.GET)
    public String nextPage(@PathVariable("pageNum") Integer pageNum, Map model) {

        int pageNext = pageNum + 1;
        int pageLast = pageNum - 1;
        int articles = (pageNum - 1) * 3;
        List<BlogPost> posts = blogPostDao.listOfThree(articles);
        boolean nextPage = blogPostDao.checkIfNextPage(articles + 3);
        System.out.println(nextPage);
        for (BlogPost blogView : posts) {

            blogView.setStringDateDisplay(sdfDisplay.format(blogView.getPostDate()));
            model.put("title", blogView.getTitle());
            model.put("date", blogView.getPostDate());
            model.put("author", blogView.getUser().getFirstName() + " " + blogView.getUser().getLastName());
            model.put("posts", posts);

        }

        List<Page> pages = pageDao.list();
        if (pageNum == 1) {
            model.put("hidden", "hidden");
        }
        model.put("pages", pages);
        model.put("pageLast", pageLast);
        model.put("pageNext", pageNext);
        model.put("nextPage", nextPage);
        return "/home";

    }

}
