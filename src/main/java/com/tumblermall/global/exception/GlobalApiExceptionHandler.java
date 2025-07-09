package com.tumblermall.global.exception;

import com.tumblermall.mainPage.exception.BannerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalApiExceptionHandler handles exceptions thrown by REST controllers
 * and converts them into standardized JSON API responses.
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Map BannerNotFoundException to a 404 Not Found response with a descriptive error message.</li>
 *   <li>Map any other unhandled exceptions to a 500 Internal Server Error response.</li>
 *   <li>Wrap error details in an ApiResponse.fail(...) structure.</li>
 * </ul>
 *
 * <p>Use this handler for JSON/REST API endpoints.
 *
 * @author sharon
 * @author whosoonhwang
 */
@RestControllerAdvice
public class GlobalApiExceptionHandler {

    /**
     * Handle BannerNotFoundException by returning HTTP 404.
     *
     * @param e the BannerNotFoundException instance
     * @return ResponseEntity with status 404 and a failure ApiResponse
     */
    @ExceptionHandler(BannerNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleBannerNotFound(BannerNotFoundException e) {
        return ResponseEntity.status(404).body(ApiResponse.fail("배너 정보를 찾을 수 없습니다."));
    }

    /**
     * Handle any other Exception by returning HTTP 500.
     *
     * @param e the exception that was thrown
     * @return ResponseEntity with status 500 and a generic failure ApiResponse
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleDefault(Exception e) {
        return ResponseEntity.status(500).body(ApiResponse.fail("서버 오류 발생"));
    }
}
