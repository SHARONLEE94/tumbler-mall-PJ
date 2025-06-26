package com.tumblermall.board.dto;

import java.util.Date;

public class BoardMainDto {
    private Integer postId;
    private String boardType;
    private String postTitle;
    private String postWriter;
    private Date writeAt;
    private Integer viewCount;
    private String isPinned;

    public String getBoardType() {return boardType;}

    public void setBoardType(String boardType) {this.boardType = boardType;}

    public String getPostTitle() {return postTitle;}

    public void setPostTitle(String postTitle) {this.postTitle = postTitle;}

    public String getPostWriter() {return postWriter;}

    public void setPostWriter(String postWriter) {this.postWriter = postWriter;}

    public Date getWriteAt() {return writeAt;}

    public void setWriteAt(Date writeAt) {this.writeAt = writeAt;}

    public Integer getViewCount() {return viewCount;}

    public void setViewCount(Integer viewCount) {this.viewCount = viewCount;}
    public Integer getPostId() {return postId;}
    public void setPostId(Integer postId) {this.postId = postId;}

    public String getIsPinned() {
        return isPinned;
    }

    public void setIsPinned(String isPinned) {
        this.isPinned = isPinned;
    }
}
