package com.mycompany.televisionblog.dto;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Category {
    
    int id;
    
    @NotEmpty
    @NotNull
    String name;
    boolean defaultCategory;

    int postCount;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public boolean isDefaultCategory() {
        return defaultCategory;
    }

    public void setDefaultCategory(boolean defaultCategory) {
        this.defaultCategory = defaultCategory;
    }
    
    
}
