package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.BlogPostDao;
import com.mycompany.televisionblog.dao.CategoryDao;
import com.mycompany.televisionblog.dao.FileUploadDao;
import com.mycompany.televisionblog.dao.PageDao;
import com.mycompany.televisionblog.dao.TagDao;
import com.mycompany.televisionblog.dao.UserDao;
import com.mycompany.televisionblog.dto.BlogPost;
import com.mycompany.televisionblog.dto.BlogPostCommand;
import com.mycompany.televisionblog.dto.Category;
import com.mycompany.televisionblog.dto.CategoryPost;
import com.mycompany.televisionblog.dto.Page;
import com.mycompany.televisionblog.dto.Tag;
import com.mycompany.televisionblog.dto.UploadedFile;
import com.mycompany.televisionblog.dto.User;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.validation.Valid;
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

    SimpleDateFormat sdfDisplay = new SimpleDateFormat("MMMM dd, yyyy");
    SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdfSQLDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private BlogPostDao blogPostDao;
    private UserDao userDao;
    private CategoryDao categoryDao;
    private PageDao pageDao;
    private FileUploadDao fileUploadDao;
    private TagDao tagDao;

    @Inject
    public BlogPostController(BlogPostDao blogPostDao, UserDao userDao, CategoryDao categoryDao, PageDao pageDao, FileUploadDao fileUploadDao, TagDao tagDao) {
        this.blogPostDao = blogPostDao;
        this.userDao = userDao;
        this.pageDao = pageDao;
        this.categoryDao = categoryDao;
        this.fileUploadDao = fileUploadDao;
        this.tagDao = tagDao;
    }

    @RequestMapping(value = "/writeBlog", method = RequestMethod.GET)
    public String writeBlogPost(Map model) {

        Date date = new Date();

        List<Page> pages = pageDao.list();
        model.put("pages", pages);
        List<Category> categories = categoryDao.list();
        model.put("categories", categories);
        List<Tag> tags = tagDao.listWithPosts();
        model.put("tags", tags);
        List<User> authors = userDao.list();
        model.put("authors", authors);
        model.put("date", sdfSQLDateTime.format(date));
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

        List<BlogPost> posts = blogPostDao.listOfNByAuthor(0, 3, author);
        List<BlogPost> latestPosts = blogPostDao.listOfN(0, 5);
        List<CategoryPost> categories = categoryDao.getPostCount();
        List<Tag> tags = tagDao.list();
        Map<String, Integer> months = blogPostDao.listOfPostMonths();
        model.put("categories", categories);

        for (BlogPost blogView : posts) {

            blogView.setStringDateDisplay(sdfDisplay.format(blogView.getPostDate()));
            model.put("title", blogView.getTitle());
            model.put("author", blogView.getUser().getFirstName() + " " + blogView.getUser().getLastName());
            model.put("posts", posts);
        }
        boolean nextPage = blogPostDao.checkIfNextPageAuthor(author, 3, 3);
        List<Page> pages = pageDao.list();
        model.put("months", months);
        model.put("authorId", author);
        model.put("pages", pages);
        model.put("tags", tags);
        model.put("pageNext", 2);
        model.put("nextPage", nextPage);
        model.put("hidden", "hidden");
        model.put("latestPosts", latestPosts);
        return "/authorBlogs";
    }

    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
    public String showByCategory(@PathVariable("category") Integer category, Map<String, Object> model) {

        List<BlogPost> posts = blogPostDao.listOfNByCategory(0, 3, category);
        List<BlogPost> latestPosts = blogPostDao.listOfN(0, 5);
        List<CategoryPost> categories = categoryDao.getPostCount();
        List<Tag> tags = tagDao.list();
        Map<String, Integer> months = blogPostDao.listOfPostMonths();
        model.put("categories", categories);

        for (BlogPost blogView : posts) {

            blogView.setStringDateDisplay(sdfDisplay.format(blogView.getPostDate()));
            model.put("title", blogView.getTitle());
            model.put("author", blogView.getUser().getFirstName() + " " + blogView.getUser().getLastName());
            model.put("posts", posts);
        }
        boolean nextPage = blogPostDao.checkIfNextPageCategory(category, 3, 3);
        List<Page> pages = pageDao.list();
        model.put("category", categoryDao.get(category).getName());
        model.put("categoryId", category);
        model.put("months", months);
        model.put("tags", tags);
        model.put("pages", pages);
        model.put("pageNext", 2);
        model.put("nextPage", nextPage);
        model.put("hidden", "hidden");
        model.put("latestPosts", latestPosts);
        return "/categoryBlogs";
    }

    @RequestMapping(value = "/tag/{tag}", method = RequestMethod.GET)
    public String showByTag(@PathVariable("tag") String tagName, Map<String, Object> model) {

        List<BlogPost> posts = blogPostDao.listOfThreeByTag(0, 3, tagName);
        List<BlogPost> latestPosts = blogPostDao.listOfN(0, 5);
        Map<String, Integer> months = blogPostDao.listOfPostMonths();
        model.put("posts", posts);
        List<String> titles = new ArrayList();
        List<String> authors = new ArrayList();
        List<CategoryPost> categories = categoryDao.getPostCount();
        List<Tag> tags = tagDao.list();
        model.put("categories", categories);

        for (BlogPost blogView : posts) {

            blogView.setStringDateDisplay(sdfDisplay.format(blogView.getPostDate()));
            titles.add(blogView.getTitle());
            authors.add(blogView.getUser().getFirstName() + " " + blogView.getUser().getLastName());

        }
        boolean nextPage = blogPostDao.checkIfNextPageTag(tagName, 3, 3);
        List<Page> pages = pageDao.list();
        model.put("tag", tagName);
        model.put("pages", pages);
        model.put("titles", titles);
        model.put("authors", authors);
        model.put("tags", tags);
        model.put("pageNext", 2);
        model.put("nextPage", nextPage);
        model.put("hidden", "hidden");
        model.put("months", months);
        model.put("latestPosts", latestPosts);
        return "/tagBlogs";
    }

    @RequestMapping(value = "/archive/{month}", method = RequestMethod.GET)
    public String showArchive(@PathVariable("month") String monthYear, Map<String, Object> model) {
        String[] monthYearParts = monthYear.split(" ");
        String month = monthYearParts[0];
        String year = monthYearParts[1];
        Map<String, Integer> months = blogPostDao.listOfPostMonths();
        List<BlogPost> posts = blogPostDao.listOfThreeByMonth(0, 3, month, year);
        List<BlogPost> latestPosts = blogPostDao.listOfN(0, 5);
        model.put("posts", posts);
        List<String> titles = new ArrayList();
        List<String> authors = new ArrayList();
        List<Tag> tags = tagDao.list();
        List<CategoryPost> categories = categoryDao.getPostCount();
        for (BlogPost blogView : posts) {

            blogView.setStringDateDisplay(sdfDisplay.format(blogView.getPostDate()));
            titles.add(blogView.getTitle());
            authors.add(blogView.getUser().getFirstName() + " " + blogView.getUser().getLastName());

        }
        boolean nextPage = blogPostDao.checkIfNextPage(0, 3);
        List<Page> pages = pageDao.list();
        model.put("monthYear", monthYear);
        model.put("month", month);
        model.put("pages", pages);
        model.put("titles", titles);
        model.put("authors", authors);
        model.put("tags", tags);
        model.put("categories", categories);
        model.put("latestPosts", latestPosts);
        model.put("months", months);
        model.put("pageNext", 2);
        model.put("nextPage", nextPage);
        model.put("hidden", "hidden");
        return "/archiveBlogs";
    }

    @RequestMapping(value = "/tag/{tag}/page/{pageNum}", method = RequestMethod.GET)
    public String nextTagPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("tag") String tag, Map model) {
        List<BlogPost> posts = blogPostDao.listOfThreeByTag((pageNum - 1) * 3, 3, tag);
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
        boolean nextPage = blogPostDao.checkIfNextPageTag(tag, articles + 3, 3);
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
        model.put("tag", tag);
        model.put("pageNext", pageNext);
        model.put("nextPage", nextPage);
        model.put("tags", tags);
        return "archiveBlogs";

    }

    @RequestMapping(value = "/archive/{monthYear}/page/{pageNum}", method = RequestMethod.GET)
    public String nextArchivePage(@PathVariable("pageNum") Integer pageNum, @PathVariable("monthYear") String monthYear, Map model) {
        String[] monthYearParts = monthYear.split(" ");
        String month = monthYearParts[0];
        String year = monthYearParts[1];
        List<BlogPost> posts = blogPostDao.listOfThreeByMonth((pageNum - 1) * 3, 3, month, year);
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
        boolean nextPage = blogPostDao.checkIfNextPageArchive(month, year, articles + 3, 3);
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
        model.put("monthYear", monthYear);
        model.put("pageNext", pageNext);
        model.put("nextPage", nextPage);
        model.put("tags", tags);
        return "archiveBlogs";

    }

    @RequestMapping(value = "/show/{postName}", method = RequestMethod.GET)
    public String showBlog(@PathVariable("postName") String postName, Map model) {

        BlogPost post = blogPostDao.get(postName);
        List<BlogPost> latestPosts = blogPostDao.listOfN(0, 5);
        List<CategoryPost> categories = categoryDao.getPostCount();
        Map<String, Integer> months = blogPostDao.listOfPostMonths();
        List<Tag> tags = tagDao.list();
        model.put("categories", categories);
        model.put("tags", tags);
        model.put("post", post);
        model.put("months", months);
        model.put("title", post.getTitle());
        model.put("date", sdfDisplay.format(post.getPostDate()));
        model.put("author", post.getUser().getFirstName() + " " + post.getUser().getLastName());
        model.put("content", post.getContent());
        model.put("category", post.getCategory().getName());
        model.put("latestPosts", latestPosts);
        List<Page> pages = pageDao.list();
        model.put("pages", pages);
        return "/blogShow";

    }

    @RequestMapping(value = "/create-blog-post/", method = RequestMethod.POST)
    @ResponseBody
    public BlogPost create(@Valid @RequestBody BlogPostCommand blogPostCommand) throws ParseException, UnsupportedEncodingException {

        blogPostCommand.setId(0);
        BlogPost blogPost = setBlogPostProperties(blogPostCommand);

        blogPost = blogPostDao.create(blogPost);

        List<String> tagList = blogPostCommand.getTagNameList();
        Integer postId = blogPost.getId();
        List<Tag> tagObjList = tagDao.list();

        linkTags(tagObjList, tagList, postId);
        return blogPost;
    }

    private void linkTags(List<Tag> tagObjList, List<String> tagList, Integer postId) {
        //checks if tag already exists and creates those that don't
        //links posts to tags
        List<String> tagNameList = new ArrayList();

        for (Tag myTag : tagObjList) {
            tagNameList.add(myTag.getName());
        }
        for (String tagName : tagList) {
            if (tagNameList.contains(tagName)) {
                Integer tagId = tagDao.getIdByName(tagName);
                tagDao.link(postId, tagId);
            } else {
                Tag tag = new Tag();
                tag.setName(tagName);
                tag = tagDao.create(tag);
                tagDao.link(postId, tag.getId());
            }
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPost(@PathVariable("id") Integer id, Map model) {

        BlogPost blogEdit = blogPostDao.get(id);
        List<Category> categories = categoryDao.list();
        List<User> authors = userDao.list();

        model.put("draft", blogEdit.isIsDraft());
        model.put("id", blogEdit.getId());
        model.put("date", blogEdit.getPostDate());
        model.put("title", blogEdit.getTitle());
        model.put("slug", blogEdit.getUrl());
        model.put("categories", categories);
        model.put("category", blogEdit.getCategory());
        model.put("authors", authors);
        model.put("author", blogEdit.getUser().getFirstName() + " " + blogEdit.getUser().getLastName());
        String content = blogEdit.getContent();
        model.put("content", content);

        List<Page> pages = pageDao.list();
        model.put("pages", pages);

        return "/editBlog";

    }

    @RequestMapping(value = "/editsubmit/", method = RequestMethod.POST)
    public String editPostSubmit(@RequestParam("id") Integer id, @RequestParam("title") String title, @RequestParam("slug") String slug, @RequestParam("author") Integer author,
            @RequestParam("category") Integer category, @RequestParam("content") String content, @RequestParam("draft") boolean draft, @RequestParam("schedule-date") String postDate, @RequestParam("expiration-date") String expirationDate, Map model) throws ParseException, UnsupportedEncodingException {

        Date date = new Date();

        BlogPost blogEdit = new BlogPost();
        blogEdit.setId(id);
        blogEdit.setTitle(title);
        blogEdit.setUrl(slug);
        blogEdit.setUser(userDao.get(author));
        blogEdit.setCategory(categoryDao.get(category));
        blogEdit.setContent(content);
        blogEdit.setPostDate(sdfSQLDateTime.parse(postDate));

        if (!expirationDate.equals("")) {
            blogEdit.setExpirationDate(sdfSQLDateTime.parse(expirationDate));
        }
        if (draft) {
            blogEdit.setApproved(false);
        } else {
            blogEdit.setApproved(true);
        }

        if (postDate.substring(10).equals(sdfSQL.format(date)) && !blogEdit.isIsDraft()) {
            blogEdit.setPostDate(sdfSQLDateTime.parse(postDate));
            blogEdit.setActive(true);
        }else if (!sdfSQLDateTime.parse(postDate).before(date) && !blogEdit.isIsDraft()){
            blogEdit.setPostDate(sdfSQLDateTime.parse(postDate));
            blogEdit.setActive(false);
            blogEdit.setApproved(false);
            
        } else if (blogEdit.isIsDraft()) {
            blogEdit.setActive(true);
            blogEdit.setApproved(false);
            blogEdit.setIsDraft(true);
            blogEdit.setPostDate(sdfSQLDateTime.parse(postDate));

        }

        blogPostDao.update(blogEdit);

        return "redirect:/admin/";

    }

    public BlogPost setBlogPostProperties(BlogPostCommand blogPostCommand) throws ParseException, UnsupportedEncodingException {

        Date date = new Date();
        String dateString = sdfSQL.format(blogPostCommand.getPostDate());
        BlogPost blogPost = new BlogPost();

        blogPost.setTitle(blogPostCommand.getTitle());
        blogPost.setUrl(blogPostCommand.getUrl());
        blogPost.setUser(userDao.get(blogPostCommand.getUserId()));
        blogPost.setCategory(categoryDao.get(blogPostCommand.getCategoryId()));
        blogPost.setContent(blogPostCommand.getContent());
        blogPost.setStringDateDisplay(sdfDisplay.format(blogPostCommand.getPostDate()));
        blogPost.setExpirationDate(blogPostCommand.getExpirationDate());
        blogPost.setId(blogPostCommand.getId());
        blogPost.setIsDraft(blogPostCommand.isIsDraft());
        blogPost.setPostDate(blogPostCommand.getPostDate());

        if (dateString.equals(sdfSQL.format(date)) && !blogPost.isIsDraft()) {
            blogPost.setPostDate(date);
            blogPost.setActive(true);
        } else if (blogPost.isIsDraft()) {
            blogPost.setActive(true);
            blogPost.setApproved(false);
            blogPost.setIsDraft(true);
            blogPost.setPostDate(date);

        }

        return blogPost;
    }

    @RequestMapping(value = "/page/{pageNum}", method = RequestMethod.GET)
    public String nextPage(@PathVariable("pageNum") Integer pageNum, Map model) {

        List<CategoryPost> categories = categoryDao.getPostCount();
        List<BlogPost> latestPosts = blogPostDao.listOfN(0, 5);
        Map<String, Integer> months = blogPostDao.listOfPostMonths();
        int pageNext = pageNum + 1;
        int pageLast = pageNum - 1;
        
        //page last sets min range of listOfN ex. Page 2 = 2 * 3, start at record 6.
        int articles = (pageLast) * 3;
        List<BlogPost> posts = blogPostDao.listOfN(articles, 3);
        List<Tag> tags = tagDao.list();
        //to check if there are articles on the next page, we increase the desired record by three.
        boolean nextPage = blogPostDao.checkIfNextPage(articles + 3, 3);
        System.out.println(nextPage);

        if (posts.isEmpty()) {

            return "redirect:/404/";
        }

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
        model.put("categories", categories);
        model.put("latestPosts", latestPosts);
        model.put("tags", tags);
        model.put("pages", pages);
        model.put("pageLast", pageLast);
        model.put("pageNext", pageNext);
        model.put("months", months);
        model.put("nextPage", nextPage);
        return "/home";

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer id) {
        BlogPost delBlog = blogPostDao.get(id);
        blogPostDao.delete(delBlog);
    }

    @RequestMapping(value = "author/{id}/page/{pageNum}", method = RequestMethod.GET)
    public String nextAuthorPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("id") String authorId, Map model) {

        List<CategoryPost> categories = categoryDao.getPostCount();
        List<BlogPost> latestPosts = blogPostDao.listOfN(0, 5);
        List<Tag> tags = tagDao.list();
        Map<String, Integer> months = blogPostDao.listOfPostMonths();
        model.put("categories", categories);
        int pageNext = pageNum + 1;
        int pageLast = pageNum - 1;
        int articles = (pageNum - 1) * 3;
        List<BlogPost> posts = blogPostDao.listOfNByAuthor(articles, 3, authorId);
        boolean nextPage = blogPostDao.checkIfNextPageAuthor(authorId, articles + 3, 3);
        System.out.println(nextPage);

        if (posts.isEmpty()) {

            return "redirect:/404/";
        }

        for (BlogPost blogView : posts) {

            blogView.setStringDateDisplay(sdfDisplay.format(blogView.getPostDate()));
            model.put("title", blogView.getTitle());
            model.put("date", blogView.getPostDate());
            model.put("author", blogView.getUser().getFirstName() + " " + blogView.getUser().getLastName());
            model.put("posts", posts);
            model.put("tags", tags);

        }

        List<Page> pages = pageDao.list();
        if (pageNum == 1) {
            model.put("hidden", "hidden");
        }
        model.put("months", months);
        model.put("pages", pages);
        model.put("latestPosts", latestPosts);
        model.put("pageLast", pageLast);
        model.put("authorId", authorId);
        model.put("pageNext", pageNext);
        model.put("nextPage", nextPage);
        return "/authorBlogs";

    }

    @RequestMapping(value = "category/{id}/page/{pageNum}", method = RequestMethod.GET)
    public String nextCategoryPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("id") Integer categoryId, Map model) {

        List<CategoryPost> categories = categoryDao.getPostCount();
        List<BlogPost> latestPosts = blogPostDao.listOfN(0, 5);
        List<Tag> tags = tagDao.list();
        Map<String, Integer> months = blogPostDao.listOfPostMonths();
        model.put("categories", categories);
        int pageNext = pageNum + 1;
        int pageLast = pageNum - 1;
        int articles = (pageNum - 1) * 3;
        List<BlogPost> posts = blogPostDao.listOfNByCategory(articles, 3, categoryId);
        boolean nextPage = blogPostDao.checkIfNextPageCategory(categoryId, articles + 3, 3);
        System.out.println(nextPage);

        if (posts.isEmpty()) {

            return "redirect:/404/";
        }

        for (BlogPost blogView : posts) {

            model.put("months", months);
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
        model.put("latestPosts", latestPosts);
        model.put("categoryId", categoryId);
        model.put("pageNext", pageNext);
        model.put("nextPage", nextPage);
        model.put("tags", tags);
        return "/categoryBlogs";

    }

    public String urlConverter(String title) throws UnsupportedEncodingException {

        String url = URLEncoder.encode(title, "UTF-8");

        return url;

    }

    @RequestMapping(value = "/grab/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BlogPost grab(@PathVariable("id") Integer id) {

        return blogPostDao.get(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public BlogPost update(@RequestBody BlogPostCommand command) throws ParseException {

        BlogPost post = new BlogPost();

        String dateTime = sdfSQLDateTime.format(command.getPostDate());

        System.out.println(dateTime);
        post.setActive(command.isActive());
        post.setApproved(command.isApproved());
        Category category = categoryDao.get(command.getCategoryId());
        post.setCategory(category);
        post.setContent(command.getContent());
        post.setExpirationDate(command.getExpirationDate());
        post.setId(command.getId());
        post.setPostDate(command.getPostDate());
        post.setTitle(command.getTitle());
        post.setUrl(command.getUrl());
        User user = userDao.get(command.getUserId());
        post.setUser(user);

        blogPostDao.update(post);

        return post;

    }

}
