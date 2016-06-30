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
    
    BlogPost get(String url);

    void update(BlogPost blogPost);

    void delete(BlogPost blogPost);

    List<BlogPost> list();
    
    List<BlogPost> listOfThree(Integer pageNum, Integer range);
    
    List<BlogPost> listOfThreeByAuthor(Integer pageNum, Integer range, String author);
    
    List<BlogPost> listOfThreeByCategory(Integer pageNum, Integer range, Integer category);
    
    List<BlogPost> listApproved();

    List<BlogPost> listUnapproved();
    
    void reassignBlogCategory(Integer defaultId, Integer originalId);
   
    boolean checkIfNextPage(Integer nextPageNum, Integer range);
    
    List<BlogPost> listOfThreeByTag(Integer pageNum, Integer range, Integer tag);
}
