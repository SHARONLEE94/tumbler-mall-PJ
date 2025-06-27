package com.tumblermall.board.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "게시판 종류 요청 객체")
public class BoardRequestDTO {

    @ApiModelProperty(value = "게시판 종류 코드", example = "NTC", required = true)
    private String boardTypeCode;

    public String getBoardTypeCode() {
        return boardTypeCode;
    }

    public String setBoardTypeCode(String boardTypeCode) {
        this.boardTypeCode = boardTypeCode;
        return boardTypeCode;
    }
}
