/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.dao;

import com.mycompany.televisionblog.dto.Tag;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface TagDao {
    
    Tag create(Tag tag);
    Tag get(Integer id);
    Integer getIdByName(String name);
    void update(Tag tag);
    void delete(Integer id);
    List<Tag> list();
    List<Tag> listWithPosts();
    void link(Integer postId, Integer tagId);
    Integer tagPostCount(String tagName);
}
