package com.sun.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by pengjikun on 2017/2/15.
 */
@SpringBootApplication  //这个等价与下面三个注解
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@EnableScheduling
@MapperScan(basePackages="com.sun.task.dao") //扫描Mapper接口
public class Start extends SpringBootServletInitializer {

    public static void main(String[] args){
        SpringApplication.run(Start.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Start.class);
    }

}
