package com.sunxs.boot.framework.interceptor;

/**
 * @author: 孙先生
 * @createTime: 2023/06/04 11:28
 * @description: 限制提交时间
 */

import com.sunxs.boot.framework.annotation.RequestLimit;
import com.sunxs.boot.framework.exception.RequestLimitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 重复请求的拦截器
 *
 * @Component：该注解将其注入到IOC容器中
 */
@Component
public class RequestLimitInterceptor implements HandlerInterceptor {
    /**
     * Redis的API
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * preHandler方法，在controller方法之前执行
     * <p>
     * 判断条件仅仅是用了uri，实际开发中根据实际情况组合一个唯一识别的条件。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            //只拦截标注了@RepeatSubmit该注解
            HandlerMethod method = (HandlerMethod) handler;
            //标注在方法上的@RepeatSubmit
            RequestLimit repeatSubmitByMethod =
                    AnnotationUtils.findAnnotation(method.getMethod(), RequestLimit.class);

            //标注在controler类上的@RepeatSubmit
            RequestLimit repeatSubmitByCls =
                    AnnotationUtils.findAnnotation(method.getMethod().getDeclaringClass(), RequestLimit.class);
            if (Objects.isNull(repeatSubmitByMethod) && Objects.isNull(repeatSubmitByCls))
                return true;
            //请求的URI
            String uri = request.getRequestURI();
            //存在即返回false，不存在即返回true
            Boolean ifAbsent = stringRedisTemplate.opsForValue().setIfAbsent(uri, "",
                    Objects.nonNull(repeatSubmitByMethod) ? repeatSubmitByMethod.seconds() : repeatSubmitByCls.seconds(),
                    TimeUnit.SECONDS);
            //如果存在，表示已经请求过了，直接抛出异常，由全局异常进行处理返回指定信息
            if (ifAbsent != null && !ifAbsent) {
                throw new RequestLimitException(Objects.nonNull(repeatSubmitByMethod) ? repeatSubmitByMethod.message() : repeatSubmitByCls.message());
            }
        }
        return true;
    }
}
