/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.Page;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface PageDao {

    Page create(Page page);

    Page get(Integer id);
    
    Page get(String url);

    void update(Page page);

    void delete(Integer id);

    List<Page> list();

}
