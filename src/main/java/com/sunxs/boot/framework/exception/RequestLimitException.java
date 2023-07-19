package com.sunxs.boot.framework.exception;

/**
 * @author: 孙先生
 * @createTime: 2023/06/04 12:00
 * @description: 重复请求异常类
 */
public class RequestLimitException extends RuntimeException{

    public RequestLimitException(String message) {
        super(message);
    }
}
