package com.hui.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pojo.Result;

/*
* 全局异常处理
* */
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    public Result ServerError(Exception exception){
        exception.printStackTrace();
        return Result.error("服务器异常，请联系管理员！");
    }
}
