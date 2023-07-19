package com.sunxs.boot.config;

import com.sunxs.boot.framework.interceptor.RequestLimitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author: 孙先生
 * @createTime: 2023/06/04 11:50
 * @description:
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private RequestLimitInterceptor repeatSubmitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
    }
}
