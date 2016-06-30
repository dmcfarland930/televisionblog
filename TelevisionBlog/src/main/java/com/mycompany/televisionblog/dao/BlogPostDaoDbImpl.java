/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.BlogPost;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class BlogPostDaoDbImpl implements BlogPostDao {

    private static final String SQL_INSERT_POST = "INSERT INTO post (title, url, user_id, category_id, content, post_date, expiration_date, active, approved) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_POST = "UPDATE post SET title = ?, url = ?, user_id = ?, category_id = ?, content = ?, post_date = ?, expiration_date = ?, active = ?, approved = ? WHERE id = ?";
    private static final String SQL_DELETE_POST = "DELETE FROM post WHERE id = ?";
    private static final String SQL_GET_POST = "SELECT * FROM post WHERE id = ?";
    private static final String SQL_GET_POST_URL = "SELECT * FROM post WHERE url = ?";
    private static final String SQL_GET_POST_LIST = "SELECT * FROM post";
    private static final String SQL_GET_POST_LIST_TITLE = "SELECT * FROM post WHERE title = ?";
    private static final String SQL_SET_POSTS_TO_ACTIVE_DATE = "UPDATE post SET active = 1 WHERE post_date <= ?";
    private static final String SQL_SET_POSTS_TO_EXPIRED_DATE = "UPDATE post SET active = 0 WHERE expiration_date <= ?";
    private static final String SQL_GET_POST_LIST_APPROVED = "SELECT * FROM post WHERE approved = 1";
    private static final String SQL_GET_POST_LIST_UNAPPROVED = "SELECT * FROM post WHERE approved = 0";
    private static final String SQL_REASSIGN_BLOG_CATEGORY_DEFAULT = "UPDATE post SET category_id = ? WHERE category_id = ?";
    private static final String SQL_GET_POST_LIST_THREE_ENTRIES = "SELECT * FROM post WHERE approved AND active ORDER BY post_date DESC LIMIT ?, ?";
    private static final String SQL_GET_POST_LIST_THREE_ENTRIES_AUTHOR = "SELECT * FROM post WHERE approved AND active AND user_id = ? ORDER BY post_date DESC LIMIT ?, ?";
    private static final String SQL_GET_POST_LIST_THREE_ENTRIES_CATEGORY = "SELECT * FROM post WHERE approved AND active AND category_id = ? ORDER BY post_date DESC LIMIT ?, ?";
//    private static final String SQL_GET_POST_LIST_DATE = "SELECT * FROM post WHERE order_date = ?";
    private static final String SQL_GET_POST_LIST_THREE_ENTRIES_TAG = "SELECT * FROM post LEFT OUTER JOIN post_tag WHERE post.approved AND post.active AND post_tag.tag_id = ? ORDER BY post.post_date DESC LIMIT ?, ?";

    private JdbcTemplate jdbcTemplate;
    private CategoryDao categoryDao;
    private UserDao userDao;

    public BlogPostDaoDbImpl(JdbcTemplate jdbcTemplate, CategoryDao categoryDao, UserDao userDao) {

        this.jdbcTemplate = jdbcTemplate;
        this.categoryDao = categoryDao;
        this.userDao = userDao;
    }

    @Override
    public BlogPost create(BlogPost blogPost) {

        try {
            blogPost = setDuplicateUrl(blogPost);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(BlogPostDaoDbImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        jdbcTemplate.update(SQL_INSERT_POST,
                blogPost.getTitle(),
                blogPost.getUrl(),
                blogPost.getUser().getId(),
                blogPost.getCategory().getId(),
                blogPost.getContent(),
                blogPost.getPostDate(),
                blogPost.getExpirationDate(),
                blogPost.isActive(),
                blogPost.isApproved());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        blogPost.setId(id);

        return blogPost;

    }

    public BlogPost setDuplicateUrl(BlogPost post) throws UnsupportedEncodingException {
        int duplicate = 0;
        String duplicateUrl;
        List<BlogPost> exists = jdbcTemplate.query(SQL_GET_POST_LIST_TITLE, new BlogPostMapper(), post.getTitle());

        if (!exists.isEmpty()) {

            for (BlogPost existingPost : exists) {

                duplicate++;

                duplicateUrl = URLEncoder.encode(post.getTitle(), "UTF-8") + "+" + duplicate;
                post.setUrl(duplicateUrl);
            }

        }
        return post;
    }

    @Override
    public BlogPost get(Integer id) {

        return jdbcTemplate.queryForObject(SQL_GET_POST, new BlogPostMapper(), id);
    }

    @Override
    public BlogPost get(String postName) {

        return jdbcTemplate.queryForObject(SQL_GET_POST_URL, new BlogPostMapper(), postName);

    }

    @Override
    public void update(BlogPost blogPost) {

        try {
            blogPost = setDuplicateUrl(blogPost);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(BlogPostDaoDbImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        jdbcTemplate.update(SQL_UPDATE_POST,
                blogPost.getTitle(),
                blogPost.getUrl(),
                blogPost.getUser().getId(),
                blogPost.getCategory().getId(),
                blogPost.getContent(),
                blogPost.getPostDate(),
                blogPost.getExpirationDate(),
                blogPost.isActive(),
                blogPost.isApproved(),
                blogPost.getId());

    }

    @Override
    public void delete(BlogPost blogPost) {
        jdbcTemplate.update(SQL_DELETE_POST, blogPost.getId());
    }

    @Override
    public List<BlogPost> list() {

        return jdbcTemplate.query(SQL_GET_POST_LIST, new BlogPostMapper());

    }

    @Override
    public List<BlogPost> listApproved() {

        return jdbcTemplate.query(SQL_GET_POST_LIST_APPROVED, new BlogPostMapper());

    }

    @Override
    public void reassignBlogCategory(Integer defaultId, Integer originalId) {

        jdbcTemplate.update(SQL_REASSIGN_BLOG_CATEGORY_DEFAULT, defaultId, originalId);

    }

    @Override
    public List<BlogPost> listUnapproved() {

        return jdbcTemplate.query(SQL_GET_POST_LIST_UNAPPROVED, new BlogPostMapper());

    }

    @Override
    public List<BlogPost> listOfThree(Integer pageNum, Integer range
    ) {

        Date date = new Date();
        jdbcTemplate.update(SQL_SET_POSTS_TO_ACTIVE_DATE, date);
        jdbcTemplate.update(SQL_SET_POSTS_TO_EXPIRED_DATE, date);

        return jdbcTemplate.query(SQL_GET_POST_LIST_THREE_ENTRIES, new BlogPostMapper(), pageNum, range);

    }

    @Override
    public List<BlogPost> listOfThreeByAuthor(Integer pageNum, Integer range, String author) {

        Date date = new Date();
        jdbcTemplate.update(SQL_SET_POSTS_TO_ACTIVE_DATE, date);
        jdbcTemplate.update(SQL_SET_POSTS_TO_EXPIRED_DATE, date);

        return jdbcTemplate.query(SQL_GET_POST_LIST_THREE_ENTRIES_AUTHOR, new BlogPostMapper(), author, pageNum, range);

    }

    @Override
    public List<BlogPost> listOfThreeByCategory(Integer pageNum, Integer range, Integer category) {

        Date date = new Date();
        jdbcTemplate.update(SQL_SET_POSTS_TO_ACTIVE_DATE, date);
        jdbcTemplate.update(SQL_SET_POSTS_TO_EXPIRED_DATE, date);

        return jdbcTemplate.query(SQL_GET_POST_LIST_THREE_ENTRIES_CATEGORY, new BlogPostMapper(), category, pageNum, range);

    }

    @Override
    public boolean checkIfNextPage(Integer nextPageNum, Integer range) {
        // checks if there are posts on next page
        List<BlogPost> nextPage = jdbcTemplate.query(SQL_GET_POST_LIST_THREE_ENTRIES, new BlogPostMapper(), nextPageNum, range);
        if (nextPage.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<BlogPost> listOfThreeByTag(Integer pageNum, Integer range, Integer tag) {

        Date date = new Date();
        jdbcTemplate.update(SQL_SET_POSTS_TO_ACTIVE_DATE, date);
        jdbcTemplate.update(SQL_SET_POSTS_TO_EXPIRED_DATE, date);

        return jdbcTemplate.query(SQL_GET_POST_LIST_THREE_ENTRIES_TAG, new BlogPostMapper(), tag, pageNum, range);
    }

    private final class BlogPostMapper implements RowMapper<BlogPost> {

        @Override
        public BlogPost mapRow(ResultSet rs, int i) throws SQLException {

            BlogPost blogPost = new BlogPost();

            blogPost.setId(rs.getInt("id"));
            blogPost.setTitle(rs.getString("title"));
            blogPost.setUrl(rs.getString("url"));
            blogPost.setUser(userDao.get(rs.getInt("user_id")));
            blogPost.setCategory(categoryDao.get(rs.getInt("category_id")));
            blogPost.setContent(rs.getString("content"));
            blogPost.setPostDate(rs.getTimestamp("post_date"));
            blogPost.setExpirationDate(rs.getDate("expiration_date"));
            blogPost.setActive(rs.getBoolean("active"));
            blogPost.setApproved(rs.getBoolean("approved"));

            return blogPost;

        }

    }

}
