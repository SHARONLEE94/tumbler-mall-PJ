package com.tumblermall.global.exception;

/**
 * 예외코드 정의
 *
 * @author sharon
 * @author whosoonhwang
 * @deprecated 사용하는 곳이 없는데? TODO
 */
@Deprecated
public enum ErrorCode {

    // 400 Bad Request
    INVALID_INPUT_VALUE(400, "잘못된 입력입니다."),
    MISSING_PARAMETER(400, "필수 파라미터가 누락되었습니다."),

    // 401 Unauthorized
    UNAUTHORIZED(401, "인증이 필요합니다."),

    // 404 Not Found
    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),
    BANNER_NOT_FOUND(404, "배너를 찾을 수 없습니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(500, "서버 오류가 발생했습니다.");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
