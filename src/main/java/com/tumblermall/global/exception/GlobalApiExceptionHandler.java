package com.tumblermall.global.exception;

import com.tumblermall.mainPage.exception.BannerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalApiExceptionHandler {

    @ExceptionHandler(BannerNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleBannerNotFound(BannerNotFoundException e) {
        return ResponseEntity.status(404).body(ApiResponse.fail("배너 정보를 찾을 수 없습니다."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleDefault(Exception e) {
        return ResponseEntity.status(500).body(ApiResponse.fail("서버 오류 발생"));
    }
}
