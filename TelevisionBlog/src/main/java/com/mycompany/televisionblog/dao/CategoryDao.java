/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.Category;
import com.mycompany.televisionblog.dto.CategoryPost;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface CategoryDao {

    Category create(Category category);

    Category get(Integer id);

    Integer getDefaultCategory();

    void update(Category category);

    void delete(Integer id);

    List<Category> list();

    List<CategoryPost> getPostCount();

}
