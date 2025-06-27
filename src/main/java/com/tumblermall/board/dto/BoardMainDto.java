package com.tumblermall.board.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "게시판 메인 요청 객체")
public class BoardMainDto {

    @ApiModelProperty(value = "게시글 id", example = "1001(게시글 고유 id)", required = true)
    private Integer postId;

    @ApiModelProperty(value = "게시판 종류 코드", example = "NTC", required = true)
    private String boardType;

    @ApiModelProperty(value = "게시글 제목", example = "서버 점검 공지", required = true)
    private String postTitle;

    @ApiModelProperty(value = "작성자 id", example = "999(관리자 id)", required = true)
    private String postWriter;

    @ApiModelProperty(value = "작성 시간", example = "2025년 05월 12일 09시", required = true)
    private Date writeAt;

    @ApiModelProperty(value = "조회수", example = "99", required = true)
    private Integer viewCount;

    @ApiModelProperty(value = "공지사항 고정 여부", example = "Y / N", required = true)
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
