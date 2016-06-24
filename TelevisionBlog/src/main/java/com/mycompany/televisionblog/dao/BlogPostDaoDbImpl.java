/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.BlogPost;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class BlogPostDaoDbImpl implements BlogPostDao {

    private static final String SQL_INSERT_POST = "INSERT INTO post (title, user_id, category_id, content, post_date, expiration_date, active, approved) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_POST = "UPDATE post SET title = ?, user_id = ?, category_id = ?, content = ?, post_date = ?, expiration_date = ?, active = ?, approved = ? WHERE id = ?";
    private static final String SQL_DELETE_POST = "DELETE FROM post WHERE id = ?";
    private static final String SQL_GET_POST = "SELECT * FROM post WHERE id = ?";
    private static final String SQL_GET_POST_NAME = "SELECT * FROM post WHERE title = ?";
    private static final String SQL_GET_POST_LIST = "SELECT * FROM post";
    private static final String SQL_GET_POST_LIST_APPROVED = "SELECT * FROM post WHERE approved = 1";
    private static final String SQL_GET_POST_LIST_UNAPPROVED = "SELECT * FROM post WHERE approved = 0";
    private static final String SQL_GET_POST_LIST_THREE_ENTRIES = "SELECT * FROM post WHERE approved = 1 GROUP BY post_date DESC LIMIT ?, 3";
    private static final String SQL_GET_POST_LIST_DATE = "SELECT * FROM post WHERE order_date = ?";

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

        jdbcTemplate.update(SQL_INSERT_POST,
                blogPost.getTitle(),
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

    @Override
    public BlogPost get(Integer id) {

        return jdbcTemplate.queryForObject(SQL_GET_POST, new BlogPostMapper(), id);
    }

    @Override
    public BlogPost get(String postName) {

        return jdbcTemplate.queryForObject(SQL_GET_POST_NAME, new BlogPostMapper(), postName);

    }

    @Override
    public void update(BlogPost blogPost) {
        jdbcTemplate.update(SQL_UPDATE_POST,
                blogPost.getTitle(),
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
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_POST, id);
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
    public List<BlogPost> listUnapproved() {

        return jdbcTemplate.query(SQL_GET_POST_LIST_UNAPPROVED, new BlogPostMapper());

    }

    @Override
    public List<BlogPost> listOfThree(Integer pageNum) {

        return jdbcTemplate.query(SQL_GET_POST_LIST_THREE_ENTRIES, new BlogPostMapper(), pageNum);

    }

    @Override
    public boolean checkIfNextPage(Integer nextPageNum) {
        // checks if there are posts on next page
        List<BlogPost> nextPage = jdbcTemplate.query(SQL_GET_POST_LIST_THREE_ENTRIES, new BlogPostMapper(), nextPageNum);
        if (nextPage.size() > 0) {
            return true;
        }
        return false;
    }

    private final class BlogPostMapper implements RowMapper<BlogPost> {

        @Override
        public BlogPost mapRow(ResultSet rs, int i) throws SQLException {

            BlogPost blogPost = new BlogPost();

            blogPost.setId(rs.getInt("id"));
            blogPost.setTitle(rs.getString("title"));
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
