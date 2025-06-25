package com.tumblermall.board.dto;

import java.util.Date;

public class BoardContextDto {
    private Integer postId;
    private String postTitle;
    private String postWriter;
    private Date postAt;
    private String postContext;
    private Integer viewCount;
    private Integer attachedFileId;

    public Integer getPostId() {
        return postId;
    }

    public Integer setPostId(Integer postId) {
        this.postId = postId;
        return postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostWriter() {
        return postWriter;
    }

    public void setPostWriter(String postWriter) {
        this.postWriter = postWriter;
    }

    public Date getPostAt() {
        return postAt;
    }

    public void setPostAt(Date postAt) {
        this.postAt = postAt;
    }

    public String getPostContext() {
        return postContext;
    }

    public void setPostContext(String postContext) {
        this.postContext = postContext;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getAttachedFileId() {
        return attachedFileId;
    }

    public void setAttachedFileId(Integer attachedFileId) {
        this.attachedFileId = attachedFileId;
    }
}
