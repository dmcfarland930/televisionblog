/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class RoleDaoDbImpl implements RoleDao {

    private static final String SQL_INSERT_ROLE = "INSERT INTO role (name) VALUES (?)";
    private static final String SQL_UPDATE_ROLE = "UPDATE role SET name = ? WHERE id = ?";
    private static final String SQL_GET_ROLE = "SELECT * FROM role WHERE id = ?";
    private static final String SQL_DELETE_ROLE = "DELETE FROM role WHERE id = ?";
    private static final String SQL_GET_ROLE_LIST = "SELECT * FROM role";
    private static final String SQL_GET_ROLES = "SELECT * from role WHERE id >= ?";

    JdbcTemplate jdbcTemplate;

    @Inject
    public RoleDaoDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Role create(Role role) {
        jdbcTemplate.update(SQL_INSERT_ROLE,
                role.getName());
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        role.setId(id);
        return role;
    }

    @Override
    public Role get(Integer id) {
        return jdbcTemplate.queryForObject(SQL_GET_ROLE, new RoleMapper(), id);
    }
    

    @Override
    public void update(Role role) {
        jdbcTemplate.update(SQL_UPDATE_ROLE,
                role.getName(),
                role.getId());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_ROLE, id);
    }

    @Override
    public List<Role> list() {
        return jdbcTemplate.query(SQL_GET_ROLE_LIST, new RoleMapper());
    }
    
    @Override
    public List<Role> getUserRoles(Integer id) {
        return jdbcTemplate.query(SQL_GET_ROLES, new RoleMapper(), id);
    }

    private static final class RoleMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int i) throws SQLException {
            Role role = new Role();

            role.setId(rs.getInt("id"));
            role.setName(rs.getString("name"));

            return role;
        }

    }

}
