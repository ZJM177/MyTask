package com.sun.core;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pengjikun on 2017/6/9.
 */
@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        /**
         * 扫描包，使其识别JAX-RS注解
         */
        packages("com.sun.jersey");
        //注册数据转换器
        register(JacksonJsonProvider.class);
    }
}
