package com.config;

import com.interceptor.TestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author cjm
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final TestInterceptor myInterceptor;

    public InterceptorConfig(TestInterceptor myInterceptor) {
        this.myInterceptor = myInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截顺序
        registry.addInterceptor(myInterceptor).addPathPatterns("/**");
    }
}