package com.mycompany.televisionblog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class BlogPostCommand {

    private Integer id;
    private String title;
    private String url;
    private int userId;
    private int categoryId;        
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "EST")
    private Date postDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "EST")
    private Date expirationDate;
    private String stringDateDisplay;
    private boolean active;
    private boolean approved;
    private List<Integer> tagIdList;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getStringDateDisplay() {
        return stringDateDisplay;
    }

    public void setStringDateDisplay(String stringDateDisplay) {
        this.stringDateDisplay = stringDateDisplay;
    }

    /**
     * @return the tagIdList
     */
    public List<Integer> getTagIdList() {
        return tagIdList;
    }

    /**
     * @param tagIdList the tagIdList to set
     */
    public void setTagIdList(List<Integer> tagIdList) {
        this.tagIdList = tagIdList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    

}
