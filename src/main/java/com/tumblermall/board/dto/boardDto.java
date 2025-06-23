package com.tumblermall.board.dto;

import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel(description = "게시판 띄워보기 중 dto")
public class boardDto {
    private String title;
    private String context;
    private String writer;
    private Date yymmdd;
    private int revCnt;

    public String getTitle() {
        return title;
    }

    public String setTitle(String title) {
        this.title = title;
        return title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getYymmdd() {
        return yymmdd;
    }

    public void setYymmdd(Date yymmdd) {
        this.yymmdd = yymmdd;
    }

    public int getRevCnt() {
        return revCnt;
    }

    public void setRevCnt(int revCnt) {
        this.revCnt = revCnt;
    }
}
