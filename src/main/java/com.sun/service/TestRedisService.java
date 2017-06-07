package com.sun.service;

import com.sun.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by pengjikun on 2017/2/18.
 */
@Service
public class TestRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public String test(){
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        HashOperations<String, String, Object> opsForHash = redisTemplate.opsForHash();
        SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();
        ZSetOperations<String, Object> opsForZSet = redisTemplate.opsForZSet();
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();

        //插入key-value,有效期20S
        opsForValue.set("key", "testvalue", 20, TimeUnit.SECONDS);
        System.out.println(opsForValue.get("key"));

        //插入对象Student
        Student student = Student.builder().name("test1").birth("2011-11-11").address("beijing").sex(1).build();
        opsForValue.set("key:student", student);
        System.out.println(opsForValue.get("key:student"));

        //hash操作，即Map
        opsForHash.put("key:map", "a", "A");
        System.out.println(opsForHash.get("key:map", "a"));
        //插入Map
        Map<String, Object> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        opsForHash.putAll("key:map", map);
        System.out.println(opsForHash.values("key:map"));






        return "test success";
    }

    public void setValue(String value){
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("test:cache:key", value);

    }

    public String getValue(){
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        return (String) opsForValue.get("test:cache:key");
    }

}
