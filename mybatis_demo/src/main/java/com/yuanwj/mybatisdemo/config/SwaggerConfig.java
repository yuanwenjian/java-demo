package com.yuanwj.mybatisdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created with Intellij IDEA
 * Author: xuziling
 * Date:  2019/1/25
 * Description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket getDocket(){
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/api/.*"))
                .build();
        return docket;
    }

    public ApiInfo apiInfo(){
        ApiInfo apiInfo=new ApiInfo(
                "mybatis-demo api document ",
                "springboot与mybatis学习",
                "0.1",
                "http://www.centaur.cn/html/products/p5/78.html",
                contact(),
                "",""
                );
        return apiInfo;
    }

    public Contact contact(){
        Contact contact=new Contact("yuanwj","yuanwj0929@gamil.com","");
        return contact;
    }
}
