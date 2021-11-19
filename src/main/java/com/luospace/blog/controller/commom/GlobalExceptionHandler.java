package com.luospace.blog.controller.commom;

import com.luospace.blog.common.Result;
import com.luospace.blog.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ExceptionHandler(MyException.class)
public class GlobalExceptionHandler {
    public Result exceptionHandler(HttpServletRequest request, MyException exception){
        return Result.failed();
    }
}
