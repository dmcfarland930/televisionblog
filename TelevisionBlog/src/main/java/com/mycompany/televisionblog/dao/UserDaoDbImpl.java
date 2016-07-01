/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author apprentice
 */
public class UserDaoDbImpl implements UserDao {

    private static final String SQL_INSERT_USER = "INSERT INTO user (first_name, last_name, username, password, group_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE user SET first_name = ?, last_name = ?, username = ?, password = ?, group_id = ? WHERE id = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM user WHERE id = ?";
    private static final String SQL_GET_USER = "SELECT * FROM user WHERE id = ?";
    private static final String SQL_GET_USER_LIST = "SELECT * FROM user";
    
    private static final String SQL_ASSIGN_USER_ROLES = "INSERT INTO authorities (user_id, role_id) VALUES (?, ?)";
    private static final String SQL_DELETE_USER_ROLES = "DELETE FROM authorities WHERE user_id = ?";
    
    private JdbcTemplate jdbcTemplate;

    public UserDaoDbImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User create(User user) {
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        jdbcTemplate.update(SQL_INSERT_USER,
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                encoder.encode(user.getPassword()),
                user.getGroupId());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        user.setId(id);

        return user;

    }

    @Override
    public User get(Integer id) {

        return jdbcTemplate.queryForObject(SQL_GET_USER, new UserMapper(), id);
    }

    @Override
    public void update(User user) {
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        jdbcTemplate.update(SQL_UPDATE_USER,
                
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                encoder.encode(user.getPassword()),
                user.getGroupId(),
                user.getId());

    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_USER, id);
    }

    @Override
    public List<User> list() {

        return jdbcTemplate.query(SQL_GET_USER_LIST, new UserMapper());

    }

    @Override
    public void assignRoles(Integer userId, Integer roleId) {
        jdbcTemplate.update(SQL_ASSIGN_USER_ROLES,
                userId,
                roleId);
    }

    @Override
    public void removeRoles(Integer userId) {
        jdbcTemplate.update(SQL_DELETE_USER_ROLES,
                userId);
    }

    private final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {

            User user = new User();

            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setGroupId(rs.getInt("group_id"));

            return user;

        }

    }

}
