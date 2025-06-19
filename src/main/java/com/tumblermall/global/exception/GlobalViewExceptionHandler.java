package com.tumblermall.global.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalViewExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleViewException(Exception e) {
        ModelAndView mav = new ModelAndView("error/error");
        mav.addObject("message", e.getMessage());
        return mav;
    }
}
