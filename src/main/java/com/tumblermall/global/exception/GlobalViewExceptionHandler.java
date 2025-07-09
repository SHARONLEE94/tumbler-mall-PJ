package com.tumblermall.global.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * GlobalViewExceptionHandler handles exceptions thrown by MVC controllers
 * and resolves them to a user-friendly error view.
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Catch all unhandled exceptions in MVC controllers.</li>
 *   <li>Populate the error view with exception details.</li>
 *   <li>Render the “error/error” template with an appropriate message.</li>
 * </ul>
 *
 * <p>Use this handler for HTML-based UI endpoints.
 *
 * @author sharon
 * @author whosoonhwang
 */
@ControllerAdvice
public class GlobalViewExceptionHandler {

    /**
     * Handle any Exception thrown within MVC controllers.
     *
     * @param e the exception that was thrown
     * @return a ModelAndView pointing to the error page with the exception message
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleViewException(Exception e) {
        ModelAndView mav = new ModelAndView("error/error");
        mav.addObject("message", e.getMessage());
        return mav;
    }
}
