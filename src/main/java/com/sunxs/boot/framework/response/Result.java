package com.sunxs.boot.framework.response;

import lombok.Data;

/**
 * @author: 孙先生
 * @createTime: 2023/06/04 11:53
 * @description: 返回结果类
 */
@Data
public class Result<T> {
    private Integer code;
    private String message;

    private T data;


    public static <T> Result<T> success(T data) {
        return restResult(data, 200, "操作成功");
    }

    public static <T> Result<T> success(String message) {
        return restResult(null, 200, message);
    }
    public static <T> Result<T> fail(String message) {
        return restResult(null, 500, message);
    }

    private static <T> Result<T> restResult(T data, Integer code, String msg) {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMessage(msg);
        return apiResult;
    }
}
