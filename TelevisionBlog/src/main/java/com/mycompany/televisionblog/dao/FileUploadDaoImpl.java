/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.Category;
import com.mycompany.televisionblog.dto.UploadedFile;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class FileUploadDaoImpl implements FileUploadDao {
    private final static String SQL_CREATE_FILE = "INSERT INTO upload (name, extension_type, bytes) VALUES (?, ?, ?)";
    private final static String SQL_GET_FILE = "SELECT * FROM upload WHERE id = ?";
    private final static String SQL_DELETE_FILE = "DELETE FROM upload WHERE id = ?";
    private final static String SQL_FILE_LIST = "SELECT * FROM upload";
    
    private JdbcTemplate jdbcTemplate;
    
    @Inject
    public FileUploadDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public UploadedFile create(UploadedFile uploadedFile) {
        jdbcTemplate.update(SQL_CREATE_FILE, 
                uploadedFile.getFileName(),
                uploadedFile.getExtensionType(),
                uploadedFile.getFileByte());
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        uploadedFile.setId(id);
        return uploadedFile;
    }

    @Override
    public UploadedFile get(Integer id) {
        return jdbcTemplate.queryForObject(SQL_GET_FILE, new UploadMapper(), id);
    }

    
    @Override
    public List<UploadedFile> list() {
        return jdbcTemplate.query(SQL_FILE_LIST, new UploadMapper());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_FILE, id);
    }
    
    private static final class UploadMapper implements RowMapper<UploadedFile> {
        @Override
        public UploadedFile mapRow(ResultSet rs, int i) throws SQLException {
            
            UploadedFile uploadedFile = new UploadedFile();
            uploadedFile.setId(rs.getInt("id"));
            uploadedFile.setFileName(rs.getString("name"));
            uploadedFile.setFileByte(rs.getBytes("bytes"));
            uploadedFile.setExtensionType(rs.getString("extension_type"));
            
            return uploadedFile;
        }
    }
    
}
