package com.mycompany.televisionblog.dto;

import java.util.Date;

/**
 *
 * @author apprentice
 */
public class BlogPostCommand {

    private int id;
    private String title;
    private int userId;
    private int categoryId;        
    private String content;
    private Date postDate;
    private Date expiratonDate;
    private boolean active;
    private boolean approved;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getExpiratonDate() {
        return expiratonDate;
    }

    public void setExpiratonDate(Date expiratonDate) {
        this.expiratonDate = expiratonDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
    
    

}
