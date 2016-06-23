/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dto.Category;
import com.mycompany.televisionblog.dto.Message;
import com.mycompany.televisionblog.dto.StatusResponse;
import com.mycompany.televisionblog.dto.UploadedFile;
import java.util.ArrayList;
import javax.validation.Valid;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    
    private static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping
	public String form() {
		return "form";
	}
	
	@RequestMapping(value="/message", method=RequestMethod.POST)
	public @ResponseBody StatusResponse message(@RequestBody Message message) {
		// Do custom steps here
		// i.e. Persist the message to the database
		logger.debug("Service processing...done");
		
		return new StatusResponse(true, "Message received");
	}
	
	@RequestMapping(value="/file", method=RequestMethod.POST)
	public @ResponseBody List<UploadedFile> upload(
			@RequestParam("file") MultipartFile file) {
		// Do custom steps here
		// i.e. Save the file to a temporary location or database
		logger.debug("Writing file to disk...done");
		
		List<UploadedFile> uploadedFiles = new ArrayList<>();
		UploadedFile u = new UploadedFile(file.getOriginalFilename(),
				Long.valueOf(file.getSize()).intValue(),
				"http://localhost:8080/spring-fileupload-tutorial/resources/"+file.getOriginalFilename());

		uploadedFiles.add(u);
                
		return uploadedFiles;
	}
}
