package com.sunxs.boot.framework.aop;

import com.sunxs.boot.framework.annotation.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author: 孙先生
 * @createTime: 2023/06/07 11:27
 * @description: 日志切面类
 */
@Component
@Aspect
@Slf4j
public class SysLogAspect {

    // 定义切点表达式
    @Pointcut("@annotation(com.sunxs.boot.framework.annotation.SysLog)")
    public void pointCut() {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, SysLog controllerLog, Object jsonResult)
    {
        log.info("描述：{}——{}",controllerLog.value(),controllerLog.type().getDesc());
        log.info("请求方法：{}",joinPoint.getSignature().getName());
        log.info("返回结果：{}",jsonResult);
    }

}