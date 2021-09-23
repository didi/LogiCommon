package com.didiglobal.logi.security.config;

import com.didiglobal.logi.security.interceptor.LogiSecurityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author cjm
 */
@Configuration("logiSecurityInterceptorConfig")
public class InterceptorConfig implements WebMvcConfigurer {

    private final LogiSecurityInterceptor logiSecurityInterceptor;

    public InterceptorConfig(LogiSecurityInterceptor logiSecurityInterceptor) {
        this.logiSecurityInterceptor = logiSecurityInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截顺序
        registry.addInterceptor(logiSecurityInterceptor).addPathPatterns("/**");

        /*
        registry.addInterceptor(logiSecurityInterceptor)
                .addPathPatterns("/**")
                // 不拦截account、swagger相关资源
                .excludePathPatterns("/v1/account/**",
                        "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
        */
    }
}