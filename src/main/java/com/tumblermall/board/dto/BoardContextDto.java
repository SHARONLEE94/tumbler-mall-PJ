package com.tumblermall.board.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
@ApiModel(description = "게시글 본문 요청 객체")
public class BoardContextDto {
    @ApiModelProperty(value = "게시글 id", example = "1001(게시글 고유 id)", required = true)
    private Integer postId;

    @ApiModelProperty(value = "제목", example = "썸머 얼리버드 이벤트", required = true)
    private String postTitle;

    @ApiModelProperty(value = "작성자", example = "999(관리자 id)", required = true)
    private String postWriter;

    @ApiModelProperty(value = "게시글 작성 시간", example = "2025년 06월 02일 09시", required = true)
    private Date postAt;

    @ApiModelProperty(value = "게시글 본문", example = "금일 오후 6시부터~", required = true)
    private String postContext;

    @ApiModelProperty(value = "조회수", example = "99", required = true)
    private Integer viewCount;

    @ApiModelProperty(value = "첨부파일 id", example = "2(첨부파일 고유 id)", required = true)
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
