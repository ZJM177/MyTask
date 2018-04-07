package com.sun.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by pengjikun on 2017/2/15.
 */
@SpringBootApplication
@EnableScheduling //可配置定时任务
@MapperScan(basePackages="com.sun.task.dao") //扫描Mapper接口
@EnableRedisHttpSession //session使用redis管理，即可session共享
public class Start extends SpringBootServletInitializer {

    public static void main(String[] args){
        SpringApplication.run(Start.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Start.class);
    }

}
