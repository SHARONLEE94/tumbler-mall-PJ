package com.tumblermall.board.dto;

public class BoardRequestDTO {
    private String boardTypeCode;

    public String getBoardTypeCode() {
        return boardTypeCode;
    }

    public String setBoardTypeCode(String boardTypeCode) {
        this.boardTypeCode = boardTypeCode;
        return boardTypeCode;
    }
}
