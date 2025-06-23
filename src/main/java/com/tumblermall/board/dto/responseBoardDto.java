package com.tumblermall.board.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "게시판 띄워보기 중 응답")
public class responseBoardDto {
    private Integer postId; //requestParam 받을 변수

    //requestParam으로 가져올 요청에 필요한 칼럼들에 대응하는 변수들
    private String boardCode;
    private String postTitle;
    private int viewCnt;
    private int isPinned;

    //getter & setter

    public Integer getPostId() {
        return postId;
    }

    public Integer setPostId(int postId) {
        this.postId = postId;
        return postId;
    }

    public int getIsPinned() {
        return isPinned;
    }

    public void setIsPinned(int isPinned) {
        this.isPinned = isPinned;
    }

    public int getVeiwCnt() {
        return viewCnt;
    }

    public void setVeiwCnt(int veiwCnt) {
        this.viewCnt = veiwCnt;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getBoardCode() {
        return boardCode;
    }

    public void setBoardCode(String boardCode) {
        this.boardCode = boardCode;
    }
}
