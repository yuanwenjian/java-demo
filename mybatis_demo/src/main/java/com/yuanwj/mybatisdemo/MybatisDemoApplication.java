package com.yuanwj.mybatisdemo;

import com.yuanwj.mybatisdemo.config.ApplicationConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan("com.yuanwj.mybatisdemo.mapper")
//@EnableDiscoveryClient
@EnableConfigurationProperties(ApplicationConfig.class)
public class MybatisDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(MybatisDemoApplication.class, args);
	}

}

