/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.BlogPost;
import java.util.List;
import java.util.Map;

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
    
    List<BlogPost> listOfN(Integer pageNum, Integer range);
    
    List<BlogPost> listOfNByAuthor(Integer pageNum, Integer range, String author);
    
    List<BlogPost> listOfNByCategory(Integer pageNum, Integer range, Integer category);
    
    List<BlogPost> listApproved();

    List<BlogPost> listUnapproved();
    
    void reassignBlogCategory(Integer defaultId, Integer originalId);
   
    boolean checkIfNextPage(Integer nextPageNum, Integer range);
    
    
    boolean checkIfNextPageAuthor(String author, Integer nextPageNum, Integer range);
    
    
    boolean checkIfNextPageCategory(Integer categoryId, Integer nextPageNum, Integer range);
    
    boolean checkIfNextPageArchive(String month, String year, Integer nextPageNum, Integer range);
    
    boolean checkIfNextPageTag(String tag, Integer nextPageNum, Integer range);
    
    boolean checkIfNextPageSearch(String searchResult, Integer nextPageNum, Integer range);
    
    boolean checkIfNextPageSearchTitle(String searchResult, Integer nextPageNum, Integer range);
    
    boolean checkIfNextPageSearchPost(String searchResult, Integer nextPageNum, Integer range);
    
    List<BlogPost> listOfThreeByTag(Integer pageNum, Integer range, String tag);
    
    List<BlogPost> listOfThreeByMonth(Integer pageNum, Integer range, String month, String year);
    
    List<BlogPost> listOfThreeBySearch(Integer pageNum, Integer range, String searchValue);
    
    List<BlogPost> listOfThreeBySearchTitle(Integer pageNum, Integer range, String searchValue);
    
    List<BlogPost> listOfThreeBySearchPost(Integer pageNum, Integer range, String searchValue);
    
    Map<String, Integer> listOfPostMonths();
}
