package com.cjw.reggie_takeout.config;

import com.cjw.reggie_takeout.util.CustomException;
import com.cjw.reggie_takeout.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author CJW
 * @date 2023/4/3
 */
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalExceptionHandle {
    /**
     * 全局统一异常处理方法
     *
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public Result<String> exceptionHandler(CustomException e) {
        return Result.error(e.getMessage());
    }
}
