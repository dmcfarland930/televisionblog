/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class CategoryDaoImpl implements CategoryDao {

    private static final String SQL_CREATE_CATEGORY = "INSERT INTO category (name) VALUES (?)";
    private static final String SQL_GET_CATEGORY = "SELECT * FROM category WHERE id = ?";
    private static final String SQL_GET_DEFAULT_CATEGORY_ID = "SELECT id FROM category WHERE default_category";
    private static final String SQL_UPDATE_CATEGORY = "UPDATE category SET name = ? WHERE id = ?";
    private static final String SQL_DELETE_CATEGORY = "DELETE FROM category WHERE id = ?";
    private static final String SQL_GET_CATEGORY_LIST = "SELECT * FROM category";
    private static final String SQL_GET_CATEGORY_POST_COUNT = "SELECT post_count FROM category where id = ?";

    private JdbcTemplate jdbcTemplate;

    public CategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Category create(Category category) {
        jdbcTemplate.update(SQL_CREATE_CATEGORY, category.getName());
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        category.setId(id);
        return category;
    }

    @Override
    public Category get(Integer id) {
        return jdbcTemplate.queryForObject(SQL_GET_CATEGORY, new CategoryMapper(), id);
    }

    @Override
    public Integer getDefaultCategory() {
        return jdbcTemplate.queryForObject(SQL_GET_DEFAULT_CATEGORY_ID, Integer.class);
    }

    @Override
    public void update(Category category) {
        jdbcTemplate.update(SQL_UPDATE_CATEGORY, category.getName(), category.getId());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_CATEGORY, id);
    }

    @Override
    public List<Category> list() {
        return jdbcTemplate.query(SQL_GET_CATEGORY_LIST, new CategoryMapper());
    }

    @Override
    public int getPostCount(Integer id) {
        return jdbcTemplate.queryForObject(SQL_GET_CATEGORY_POST_COUNT, Integer.class, id);
    }

    private static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {

            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setPostCount(rs.getInt("post_count"));
            category.setDefaultCategory(rs.getBoolean("default_category"));

            return category;
        }
    }
}
