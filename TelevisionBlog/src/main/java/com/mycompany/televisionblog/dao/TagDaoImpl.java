/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.Category;
import com.mycompany.televisionblog.dto.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class TagDaoImpl implements TagDao {
    private static final String SQL_CREATE_TAG = "INSERT INTO tag (name) VALUES (?)";
    private static final String SQL_CREATE_POST_TAG = "INSERT INTO post_tag (tag_id, post_id) VALUES  (?,?)";
    private static final String SQL_UPDATE_TAG = "UPDATE tag SET name = ? WHERE id = ?";
    private static final String SQL_DELETE_TAG = "DELETE FROM tag WHERE id = ?";
    private static final String SQL_GET_TAG_BY_NAME = "SELECT id FROM tag WHERE name = ?";
    private static final String SQL_GET_TAG = "SELECT * FROM tag WHERE id = ?";
    private static final String SQL_GET_TAG_LIST = "SELECT * FROM tag";
    private static final String SQL_GET_TAG_LIST_WITH_POSTS = "SELECT tag.name, tag.id FROM tag JOIN post_tag ON tag.id = post_tag.tag_id GROUP BY post_tag.tag_id HAVING count(post_tag.post_id) > 0";
    private static final String SQL_GET_TAG_POST_COUNT = "SELECT COUNT(post_tag.post_id) FROM tag LEFT OUTER JOIN post_tag ON post_tag.tag_id = tag.id WHERE tag.name = ?;";
    private JdbcTemplate jdbcTemplate;
    
    public TagDaoImpl (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Tag create(Tag tag) {
//        List<Tag> tagList = jdbcTemplate.query(SQL_GET_TAG_BY_NAME, new TagMapper(), tag.getTagName());
        jdbcTemplate.update(SQL_CREATE_TAG, tag.getName());
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        tag.setId(id);
        return tag;
    }

    @Override
    public Tag get(Integer id) {
        return jdbcTemplate.queryForObject(SQL_GET_TAG, new TagMapper(), id);
    }
    
    @Override
    public Integer getIdByName(String name) {
        System.out.println(name);
        return jdbcTemplate.queryForObject(SQL_GET_TAG_BY_NAME, Integer.class, name);
    }

    @Override
    public void update(Tag tag) {
        jdbcTemplate.update(SQL_UPDATE_TAG, tag.getName(), tag.getId());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_TAG, id);
    }

    @Override
    public List<Tag> list() {
        return jdbcTemplate.query(SQL_GET_TAG_LIST, new TagMapper());
    }

    @Override
    public void link(Integer postId, Integer tagId) {
        jdbcTemplate.update(SQL_CREATE_POST_TAG, tagId, postId);
    }

    @Override
    public Integer tagPostCount(String tagName) {
        return jdbcTemplate.queryForObject(SQL_GET_TAG_POST_COUNT, Integer.class, tagName);
    }

    @Override
    public List<Tag> listWithPosts() {
        return jdbcTemplate.query(SQL_GET_TAG_LIST_WITH_POSTS, new TagMapper());
    }
    private static final class TagMapper implements RowMapper<Tag> {
        @Override
        public Tag mapRow(ResultSet rs, int i) throws SQLException {
            
            Tag tag = new Tag();
            tag.setId(rs.getInt("id"));
            tag.setName(rs.getString("name"));
            
            
            return tag;
        }
    }
}
