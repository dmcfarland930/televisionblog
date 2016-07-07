/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.Page;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class PageDaoDbImpl implements PageDao {

    JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_PAGE = "INSERT INTO page (name, url, content, active) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_PAGE = "UPDATE page SET name = ?, url = ?, content = ?, position = ?, active = ? WHERE id = ?";
    private static final String SQL_GET_PAGE = "SELECT * FROM page WHERE id = ?";
    private static final String SQL_GET_PAGE_URL = "SELECT * FROM page WHERE url = ?";
    private static final String SQL_DELETE_PAGE = "DELETE FROM page WHERE id = ?";
    private static final String SQL_GET_PAGE_LIST = "SELECT * FROM page ORDER BY position ASC";

    public PageDaoDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Page create(Page page) {
        jdbcTemplate.update(SQL_INSERT_PAGE,
                page.getName(),
                page.getUrl(),
                page.getContent(),
                page.isActive());
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        page.setId(id);

        return page;
    }

    @Override
    public Page get(Integer id) {
        return jdbcTemplate.queryForObject(SQL_GET_PAGE, new PageMapper(), id);
    }
    
    @Override
    public Page get(String url) {
        return jdbcTemplate.queryForObject(SQL_GET_PAGE_URL, new PageMapper(), url);
    }

    @Override
    public void update(Page page) {
        jdbcTemplate.update(SQL_UPDATE_PAGE,
                page.getName(),
                page.getUrl(),
                page.getContent(),
                page.getPosition(),
                page.isActive(),
                page.getId());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_PAGE, id);
    }

    @Override
    public List<Page> list() {
        return jdbcTemplate.query(SQL_GET_PAGE_LIST, new PageMapper());
    }

    private static final class PageMapper implements RowMapper<Page> {

        @Override
        public Page mapRow(ResultSet rs, int i) throws SQLException {

            Page page = new Page();

            page.setId(rs.getInt("id"));
            page.setName(rs.getString("name"));
            page.setUrl(rs.getString("url"));
            page.setContent(rs.getString("content"));
            page.setPosition(rs.getInt("position"));
            page.setActive(rs.getBoolean("active"));

            return page;
        }

    }

}
