/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.UserRight;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class UserRightDaoDbImpl implements UserRightDao {

    private static final String SQL_INSERT_RIGHT = "INSERT INTO user_right (name, group_name) VALUE (?, ?)";
    private static final String SQL_UPDATE_RIGHT = "UPDATE user_right SET name = ? WHERE id = ?";
    private static final String SQL_GET_RIGHT = "SELECT * FROM user_right WHERE id = ?";
    private static final String SQL_DELETE_RIGHT = "DELETE FROM user_right WHERE id = ?";
    private static final String SQL_DELETE_RIGHTS_BY_ROLE = "DELETE FROM role_user_right WHERE role_id = ?";
    private static final String SQL_GET_RIGHT_LIST = "SELECT * FROM user_right";
    private static final String SQL_GET_BY_ROLE = "SELECT u.id, u.name, u.group_name FROM user_right u INNER JOIN role_user_right ru ON ru.user_right_id = u.id INNER JOIN role r ON ru.role_id = r.id WHERE r.id = ?";
    private static final String SQL_GET_BY_ROLE_ID = "SELECT u.id FROM user_right u INNER JOIN role_user_right ru ON ru.user_right_id = u.id INNER JOIN role r ON ru.role_id = r.id WHERE r.id = ? AND u.group_name = ? ORDER BY u.id ASC";
    private static final String SQL_DELETE_BY_ROLE = "DELETE FROM role_user_right WHERE role_id = ? AND user_right_id = ?";

    private static final String SQL_INSERT_ROLE_RIGHT = "INSERT INTO role_user_right (role_id, user_right_id) VALUE (?, ?)";
    private static final String SQL_LIST_BY_GROUP = "SELECT * FROM user_right WHERE group_name = ? ORDER BY id ASC";

    JdbcTemplate jdbcTemplate;

    public UserRightDaoDbImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserRight create(UserRight userRight) {
        jdbcTemplate.update(SQL_INSERT_RIGHT,
                userRight.getName(),
                userRight.getGroupName());
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        userRight.setId(id);

        return userRight;
    }

    @Override
    public UserRight get(Integer id) {
        return jdbcTemplate.queryForObject(SQL_GET_RIGHT, new RightMapper(), id);
    }

    @Override
    public void update(UserRight userRight) {
        jdbcTemplate.update(SQL_UPDATE_RIGHT,
                userRight.getName(),
                userRight.getId());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_RIGHT, id);
    }

    @Override
    public List<UserRight> list() {
        return jdbcTemplate.query(SQL_GET_RIGHT_LIST, new RightMapper());
    }

    @Override
    public List<UserRight> listRoleRights(Integer id) {
        return jdbcTemplate.query(SQL_GET_BY_ROLE, new RightMapper(), id);
    }

    @Override
    public void deleteRoleRight(Integer roleId, Integer rightId) {
        jdbcTemplate.update(SQL_DELETE_BY_ROLE, roleId, rightId);
    }

    @Override
    public void createRoleRight(Integer roleId, Integer rightId) {
        jdbcTemplate.update(SQL_INSERT_ROLE_RIGHT,
                roleId,
                rightId);
    }

    @Override
    public List<UserRight> listByGroup(String group) {
        return jdbcTemplate.query(SQL_LIST_BY_GROUP, new RightMapper(), group.toUpperCase());
    }

    @Override
    public List<Integer> listRoleRightsByIdGroup(Integer id, String group) {
        return jdbcTemplate.query(SQL_GET_BY_ROLE_ID, new RightMapperId(), id, group.toUpperCase());
    }

    @Override
    public void deleteByRole(Integer roleId) {
        jdbcTemplate.update(SQL_DELETE_RIGHTS_BY_ROLE, roleId);
    }

    private static final class RightMapperId implements RowMapper<Integer> {

        @Override
        public Integer mapRow(ResultSet rs, int i) throws SQLException {
            return rs.getInt("id");
        }

    }

    private static final class RightMapper implements RowMapper<UserRight> {

        @Override
        public UserRight mapRow(ResultSet rs, int i) throws SQLException {

            UserRight userRight = new UserRight();
            String output = "";
            String name = "";

            userRight.setId(rs.getInt("id"));

            Pattern p = Pattern.compile("\\w_([a-zA-Z]+)_\\w");
            Matcher m = p.matcher(rs.getString("name"));
            while (m.find()) {
                output = m.group(1);
            }

            try {
                name = output.substring(0, 1).toUpperCase();
            } catch (StringIndexOutOfBoundsException e) {

            }

            userRight.setGroupName(rs.getString("group_name"));
            userRight.setName(name);

            return userRight;
        }

    }

}
