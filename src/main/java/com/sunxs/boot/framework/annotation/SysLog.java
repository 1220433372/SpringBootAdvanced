package com.sunxs.boot.framework.annotation;

import com.sunxs.boot.framework.enums.SysLogEnum;

import java.lang.annotation.*;

/**
 * @author: 孙先生
 * @createTime: 2023/06/07 11:26
 * @description: 系统操作日志切面
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    /**
     * 描述
     *
     * @return
     */
    String value() default "";


    SysLogEnum type() default SysLogEnum.OTHER;
}
