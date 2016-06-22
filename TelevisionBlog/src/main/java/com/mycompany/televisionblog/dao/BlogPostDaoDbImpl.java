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

    private static final String SQL_INSERT_POST = "INSERT INTO post (title, user_id, category_id, content, post_date, expiration_date, approved) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_POST = "UPDATE post SET title = ?, user_id = ?, category_id = ?, content = ?, post_date = ?, expiration_date = ?, approved = ? WHERE id = ?";
    private static final String SQL_DELETE_POST = "DELETE FROM post WHERE id = ?";
    private static final String SQL_GET_POST = "SELECT * FROM post WHERE id = ?";
    private static final String SQL_GET_BLOG_POST = "SELECT * FROM post";
    private static final String SQL_GET_POST_LIST_DATE = "SELECT * FROM post WHERE order_date = ?";

    private JdbcTemplate jdbcTemplate;
    private BlogPostDao blogPostDao;
    private CategoryDao categoryDao;
    private UserDao userDao;
    
    public BlogPostDaoDbImpl(JdbcTemplate jdbcTemplate, BlogPostDao blogPostDao, CategoryDao categoryDao, UserDao userDao) {

        this.jdbcTemplate = jdbcTemplate;
        this.blogPostDao = blogPostDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
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
    public void update(BlogPost blogPost) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BlogPost> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            blogPost.setPostDate(rs.getDate("post_date"));
            blogPost.setExpirationDate(rs.getDate("expiration_date"));
            blogPost.setApproved(rs.getBoolean(""));

            return blogPost;

        }

    }

}
