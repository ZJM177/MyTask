package com.sun.task.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by pengjikun on 2018/4/7.
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
}
