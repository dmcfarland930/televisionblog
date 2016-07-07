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

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.inject.Inject;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
    
    
    @RequestMapping(value = "/send-script", method = RequestMethod.POST)
    @ResponseBody
    public void sendScript (HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile, @RequestParam("sender") String sender, @RequestParam("email") String email) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/");
        String fileNamePart = multipartFile.getOriginalFilename();
        String filename = path + fileNamePart;
        System.out.println(filename);
        File file = new File(filename);
        System.out.println(multipartFile);
        multipartFile.transferTo(file);
        sendScript(email, sender, filename, fileNamePart);
        sendVerification(email, sender);
    }
    private final String username = "patstvblog@gmail.com";
    private final String password = "patstvblog2";

    
    private void sendScript(String email, String sender, String filename, String fileNamePart) {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("patstvblog@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("patstvblog@gmail.com"));
            message.setSubject("Script Received");
            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            
            // Create a multipar message
             Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);
            // Now set the actual message
            messageBodyPart.setText("Dear Pat,"
                + "\n\n You received a script from " + sender + "!"
                + "\n\n You can reply at " + email + "!");
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileNamePart);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);
            Transport.send(message);

            System.out.println("Sent email!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    private void sendVerification (String email, String sender) {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("patstvblog@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
            message.setSubject("Script Received");
            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            
            // Create a multipar message
             Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);
            // Now set the actual message
            messageBodyPart.setText("Dear " + sender + ","
                + "\n\n This email is to verify that Pat Toner received your script!");
            
            message.setContent(multipart);
            Transport.send(message);

            System.out.println("Sent email!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    @RequestMapping(value = "/send-script", method = RequestMethod.GET)
    public String sendScript () {
        return "emailScript";
    }
}

