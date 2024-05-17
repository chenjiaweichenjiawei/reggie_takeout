package com.cjw.reggie_takeout.util;

/**
 * 自定义异常类
 *
 * @author CJW
 * @date 2023/4/11
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
