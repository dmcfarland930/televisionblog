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

        Date date = new Date();
        String inputDate = sdfSQL.format(date);
        String dateOnly = inputDate.substring(0, inputDate.length() - 9);

        List<Page> pages = pageDao.list();
        model.put("pages", pages);
        List<Category> categories = categoryDao.list();
        model.put("categories", categories);
        List<User> authors = userDao.list();
        model.put("authors", authors);
        model.put("date", dateOnly);
        List<UploadedFile> fileList = fileUploadDao.list();
        List<Integer> idList = new ArrayList<>();
        for (UploadedFile uf : fileList) {
            idList.add(uf.getId());
        }
        model.put("idList", idList);
        return "writeBlog";
    }

    @RequestMapping(value = "/author/{author}", method = RequestMethod.GET)
    public String showByAuthor(@PathVariable("author") String author, Map<String, Object> model) {

        List<BlogPost> posts = blogPostDao.listOfThreeByAuthor(0, author);

        for (BlogPost blogView : posts) {

            blogView.setStringDateDisplay(sdfDisplay.format(blogView.getPostDate()));
            model.put("title", blogView.getTitle());
            model.put("author", blogView.getUser().getFirstName() + " " + blogView.getUser().getLastName());
            model.put("posts", posts);
        }
        boolean nextPage = blogPostDao.checkIfNextPage(3);
        List<Page> pages = pageDao.list();
        model.put("authorId", author);
        model.put("pages", pages);
        model.put("pageNext", 2);
        model.put("nextPage", nextPage);
        model.put("hidden", "hidden");
        return "/authorBlogs";
    }

    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
    public String showByCategory(@PathVariable("category") Integer category, Map<String, Object> model) {

        List<BlogPost> posts = blogPostDao.listOfThreeByCategory(0, category);

        for (BlogPost blogView : posts) {

            blogView.setStringDateDisplay(sdfDisplay.format(blogView.getPostDate()));
            model.put("title", blogView.getTitle());
            model.put("author", blogView.getUser().getFirstName() + " " + blogView.getUser().getLastName());
            model.put("posts", posts);
        }
        boolean nextPage = blogPostDao.checkIfNextPage(3);
        List<Page> pages = pageDao.list();
        model.put("category", categoryDao.get(category).getName());
        model.put("categoryId", category);
        model.put("pages", pages);
        model.put("pageNext", 2);
        model.put("nextPage", nextPage);
        model.put("hidden", "hidden");
        return "/categoryBlogs";
    }

    @RequestMapping(value = "/{postName}", method = RequestMethod.GET)
    public String showBlog(@PathVariable("postName") String postName, Map model) {

        BlogPost post = blogPostDao.get(postName);

        model.put("title", post.getTitle());
        model.put("date", sdfDisplay.format(post.getPostDate()));
        model.put("author", post.getUser().getFirstName() + " " + post.getUser().getLastName());
        model.put("content", post.getContent());
        model.put("category", post.getCategory().getName());

        List<Page> pages = pageDao.list();
        model.put("pages", pages);
        return "/blogShow";

    }

    @RequestMapping(value = "/create-blog-post/", method = RequestMethod.POST)
    @ResponseBody
    public BlogPost create(@RequestBody BlogPostCommand blogPostCommand) throws ParseException {

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

    public BlogPost setBlogPostProperties(BlogPostCommand blogPostCommand) throws ParseException {

        Date time = new Date();
        String timeString = sdfSQL.format(time).substring(10);
        String dateString = sdfSQL.format(blogPostCommand.getPostDate()).substring(0, 10);
        String dateTime = dateString + timeString;
        BlogPost blogPost = new BlogPost();

        blogPost.setTitle(blogPostCommand.getTitle());
        blogPost.setUser(userDao.get(blogPostCommand.getUserId()));
        blogPost.setCategory(categoryDao.get(blogPostCommand.getCategoryId()));
        blogPost.setContent(blogPostCommand.getContent());
        blogPost.setPostDate(sdfSQL.parse(dateTime));
        blogPost.setStringDateDisplay(sdfDisplay.format(blogPostCommand.getPostDate()));
        blogPost.setExpirationDate(blogPostCommand.getExpirationDate());
        blogPost.setId(blogPostCommand.getId());

        if (dateString.equals(sdfSQL.format(time).substring(0, 10))) {
            blogPost.setActive(true);
        }

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
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer id) {
        blogPostDao.delete(id);
    }
    
    @RequestMapping(value="/", method=RequestMethod.PUT)
    @ResponseBody
    public BlogPost update(@RequestBody BlogPostCommand command) throws ParseException {
      
        BlogPost post = this.setBlogPostProperties(command);
        
        blogPostDao.update(post);
        
        return post;
        
    };
    
    @RequestMapping(value="/grab/{id}", method=RequestMethod.GET)
    @ResponseBody
    public BlogPost get(@PathVariable("id") Integer id) {
        return blogPostDao.get(id);     
    };

    @RequestMapping(value = "author/{id}/page/{pageNum}", method = RequestMethod.GET)
    public String nextAuthorPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("id") String authorId, Map model) {

        int pageNext = pageNum + 1;
        int pageLast = pageNum - 1;
        int articles = (pageNum - 1) * 3;
        List<BlogPost> posts = blogPostDao.listOfThreeByAuthor(articles, authorId);
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
        model.put("authorId", authorId);
        model.put("pageNext", pageNext);
        model.put("nextPage", nextPage);
        return "/authorBlogs";

    }

    @RequestMapping(value = "category/{id}/page/{pageNum}", method = RequestMethod.GET)
    public String nextCategooryPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("id") Integer categoryId, Map model) {

        int pageNext = pageNum + 1;
        int pageLast = pageNum - 1;
        int articles = (pageNum - 1) * 3;
        List<BlogPost> posts = blogPostDao.listOfThreeByCategory(articles, categoryId);
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
        model.put("category", categoryDao.get(categoryId).getName());
        model.put("pages", pages);
        model.put("pageLast", pageLast);
        model.put("categoryId", categoryId);
        model.put("pageNext", pageNext);
        model.put("nextPage", nextPage);
        return "/categoryBlogs";

    }
}
