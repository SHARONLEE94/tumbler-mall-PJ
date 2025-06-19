package com.tumblermall.mainPage.exception;

public class BannerNotFoundException extends RuntimeException {
    public BannerNotFoundException() {
        super("배너 정보를 찾을 수 없습니다.");
    }
}