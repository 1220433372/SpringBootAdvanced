package com.sunxs.boot.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: 孙先生
 * @createTime: 2023/06/04 11:27
 * @description:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestLimit {
    /**
     * 默认失效时间5秒
     */
    long seconds() default 5;
    String message() default "不可重复请求";
}
