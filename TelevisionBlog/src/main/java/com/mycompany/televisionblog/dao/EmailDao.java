/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import java.io.File;


/**
 *
 * @author apprentice
 */
public interface EmailDao {
    public void sendScript(String sender, String fileName, String fileNamePart);
    
}
