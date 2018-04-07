package com.sun.task;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * Created by pengjikun on 2018/4/7.
 */
public class RedisTest extends BasicTest{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test(){
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String key = "name";
        operations.set(key, "stand");
        String str = operations.get(key);
        System.out.println(str);
    }

}
