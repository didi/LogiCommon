package com.didiglobal.logi.job.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan({"com.didiglobal.logi.job", "com.didiglobal.logi"})
@SpringBootApplication
@EnableSwagger2
public class RestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class);
    }
}