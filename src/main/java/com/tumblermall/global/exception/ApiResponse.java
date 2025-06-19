package com.tumblermall.global.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "공통 API 응답")
public class ApiResponse<T> {

    @ApiModelProperty(value = "응답 코드", example = "200")
    private int code;

    @ApiModelProperty(value = "응답 메시지", example = "성공")
    private String message;

    @ApiModelProperty(value = "실제 데이터")
    private T data;

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "성공", data);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(400, message, null);
    }
}
