package com.tumblermall.board.vo;

public class ResponseTempSessionVo {
    private String id;
    private String password;
    private String role;

    public String setVo(String input){
        role = input;
        return role;
    }
    public String getVo(){
        return role;
    }
}
