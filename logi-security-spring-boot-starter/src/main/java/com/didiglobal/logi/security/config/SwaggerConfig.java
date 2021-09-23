package com.didiglobal.logi.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration("logiSecuritySwaggerConfig")
@EnableSwagger2
public class SwaggerConfig {

    @Bean("logiSecurityCreateRestApi")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("logi-security")
                .apiInfo(apiInfo())
                .select()
                // swagger要扫描的包路径
                .apis(RequestHandlerSelectors.basePackage("com.didiglobal.logi.security.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("logi-security在线文档")
                .description(("用户、项目、角色、部门、界面权限、资源权限、操作日志、消息通知"))
                // 作者信息
                .contact(new Contact("home","",""))
                .version("v1.0")
                .build();
    }

}