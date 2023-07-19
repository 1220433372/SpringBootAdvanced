package com.sunxs.boot.framework.exception;

/**
 * 业务异常
 *
 * @author 2023/06/04 11:58
 * @date 2018-11-08
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
