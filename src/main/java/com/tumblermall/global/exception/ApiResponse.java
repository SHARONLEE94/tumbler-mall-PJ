package com.tumblermall.global.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

/**
 * ApiResponse is a generic wrapper for standardizing API responses.
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Encapsulate the HTTP response code.</li>
 *   <li>Provide a human-readable message describing the result.</li>
 *   <li>Carry the actual payload data when the request is successful.</li>
 * </ul>
 *
 * @param <T> the type of the response payload
 *
 * @author sharon
 * @author whosoonhwang
 */
@ApiModel(description = "공통 API 응답")
public class ApiResponse<T> {

    @ApiModelProperty(value = "응답 코드", example = "200")
    private int code;

    @ApiModelProperty(value = "응답 메시지", example = "성공")
    private String message;

    @ApiModelProperty(value = "실제 데이터")
    private T data;

    /**
     * Constructs a new ApiResponse with the given code, message, and data.
     *
     * @param code    the HTTP-style response code
     * @param message a descriptive message
     * @param data    the payload data, or null if none
     */
    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * Creates a successful ApiResponse containing the provided data.
     *
     * @param data the payload data
     * @param <T>  the type of the payload
     * @return an ApiResponse with code 200 and message "성공"
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(HttpStatus.OK.value(), "성공", data);
    }

    /**
     * Creates a failure ApiResponse with the given message.
     *
     * @param message the error message
     * @param <T>     the type parameter for the payload (will be null)
     * @return an ApiResponse with code 400 and the provided error message
     */
    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), message, null);
    }

    // Getters and setters omitted for brevity
}
