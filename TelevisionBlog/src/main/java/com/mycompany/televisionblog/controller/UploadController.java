/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.FileUploadDao;
import com.mycompany.televisionblog.dto.UploadedFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    private FileUploadDao fileUploadDao;
    
    @Inject
    public UploadController(FileUploadDao fileUploadDao) {
        this.fileUploadDao = fileUploadDao;
    }
	@RequestMapping
	public String form() {
		return "form";
	}
	@RequestMapping(value="", method = RequestMethod.POST)
        @ResponseBody
        public UploadedFile doUpload(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile) throws IOException {
                System.out.println(request.getSession().getServletContext().getRealPath("/"));
                UploadedFile file = new UploadedFile();
                file.setFileName(multipartFile.getOriginalFilename());
                file.setFileByte(multipartFile.getBytes());
                file.setExtensionType(multipartFile.getContentType());
                fileUploadDao.create(file);
                return file;
        }
        @RequestMapping(value = "showImage/{id}", method = RequestMethod.GET)
        public void showImage(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException   {
        
            UploadedFile file = fileUploadDao.get(id);
            IOUtils.copy(new ByteArrayInputStream(file.getFileByte()), response.getOutputStream());
            response.setContentType(file.getExtensionType());
        
        }
        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
        @ResponseBody
        public void deleteImage(@PathVariable("id") Integer id) {
        
            fileUploadDao.delete(id);
        }
}
