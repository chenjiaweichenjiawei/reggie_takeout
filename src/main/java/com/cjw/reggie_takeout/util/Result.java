package com.cjw.reggie_takeout.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CJW
 * @date 2023/3/28
 */
@Data
public class Result<T> {
    /**
     * 状态码，1表示成功，0和其他数字表示失败
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 返回结果携带的数据
     */
    private T data;
    /**
     * 返回结果携带的动态数据
     */
    private Map map = new HashMap();

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.data = object;
        result.code = 1;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.code = 0;
        result.msg = msg;
        return result;
    }

    public Result<T> add(String key, String value) {
        this.map.put(key, value);
        return this;
    }
}














