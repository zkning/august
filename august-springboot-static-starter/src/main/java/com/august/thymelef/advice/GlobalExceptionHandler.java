package com.august.thymelef.advice;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@ConditionalOnMissingClass
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ModelAndView exceptionHandler(Exception ex) {
        if (ex instanceof NoHandlerFoundException) {
            return new ModelAndView("template/tips/404");
        }
        return new ModelAndView("template/tips/error");
    }
}
