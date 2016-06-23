/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.BlogPost;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface BlogPostDao {

    BlogPost create(BlogPost blogPost);

    BlogPost get(Integer id);

    void update(BlogPost blogPost);

    void delete(Integer id);

    List<BlogPost> list();

    List<BlogPost> listOfThree(Integer pageNum);
}
