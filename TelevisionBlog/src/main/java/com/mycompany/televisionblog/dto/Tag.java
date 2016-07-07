package com.mycompany.televisionblog.dto;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Tag {
    private int id;
    @NotEmpty(message="You must supply a Tag Name")
    private String name;

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
    
    
}
