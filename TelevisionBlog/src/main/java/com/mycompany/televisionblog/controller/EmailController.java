/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.controller;

/**
 *
 * @author apprentice
 */
import com.mycompany.televisionblog.dao.EmailDao;
import java.io.File;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/contact")
public class EmailController {
    
    private EmailDao emailDao;
    
    @Inject
    public EmailController (EmailDao emailDao) {
        this.emailDao = emailDao;
    }
    
    @RequestMapping(value = "/send-script", method = RequestMethod.POST)
    @ResponseBody
    public void sendScript (HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile, @RequestParam("sender") String sender) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/");
        String fileNamePart = multipartFile.getOriginalFilename();
        String filename = path + fileNamePart;
        System.out.println(filename);
        File file = new File(filename);
        System.out.println(multipartFile);
        multipartFile.transferTo(file);
        emailDao.sendScript(sender, filename, fileNamePart);
    }
    
    @RequestMapping(value = "/send-script", method = RequestMethod.GET)
    public String sendScript () {
        return "emailScript";
    }
}

