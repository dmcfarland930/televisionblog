/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.UploadedFile;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FileUploadDao {
    public UploadedFile create(UploadedFile uploadedFile);
    public UploadedFile get(Integer id);
    public List<UploadedFile> list();
    public void delete(Integer id);
}
