/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.Page;
import com.mycompany.televisionblog.dto.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class PageDaoImpl implements PageDao {

    JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_PAGE = "INSERT INTO pages (name, url, content, user_id) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_PAGE = "UPDATE pages SET name = ?, url = ?, content = ?, userId = ? WHERE id = ?";
    private static final String SQL_GET_PAGE = "SELECT * FROM pages WHERE id = ?";
    private static final String SQL_DELETE_PAGE = "DELETE * FROM pages WHERE id = ?";
    private static final String SQL_GET_PAGE_LIST = "SELECT * FROM pages";

    public PageDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page create(Page page) {
        jdbcTemplate.update(SQL_INSERT_PAGE,
                page.getName(),
                page.getUrl(),
                page.getContent(),
                page.getUser().getId());
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        page.setId(id);

        return page;
    }

    @Override
    public Page get(Integer id) {
        return jdbcTemplate.queryForObject(SQL_GET_PAGE, new PageMapper(), id);
    }

    @Override
    public void update(Page page) {
        jdbcTemplate.update(SQL_UPDATE_PAGE,
                page.getName(),
                page.getUrl(),
                page.getContent(),
                page.getUser().getId(),
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
            User user = new User();
            
            user.setId(rs.getInt("user_id"));

            page.setId(rs.getInt("id"));
            page.setName(rs.getString("name"));
            page.setUrl(rs.getString("url"));
            page.setContent(rs.getString("content"));
            page.setUser(user);

            return page;
        }

    }

}
