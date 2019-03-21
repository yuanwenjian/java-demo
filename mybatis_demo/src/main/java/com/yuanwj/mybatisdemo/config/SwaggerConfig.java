package com.yuanwj.mybatisdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import javax.annotation.Resource;

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

    @Resource
    private ApplicationConfig applicationConfig;

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
                applicationConfig.getSwagger().getTitle(),
                applicationConfig.getSwagger().getDescription(),
                applicationConfig.getSwagger().getVersion(),
                applicationConfig.getSwagger().getServiceUrl(),
                contact(),
                "",""
                );
        return apiInfo;
    }

    public Contact contact(){
        Contact contact=new Contact(applicationConfig.getSwagger().getName(),
                applicationConfig.getSwagger().getServiceUrl(),applicationConfig.getSwagger().getEmail());
        return contact;
    }
}
