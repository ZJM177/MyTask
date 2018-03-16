package com.sun.task;

import com.couchbase.client.CouchbaseClient;
import com.sun.task.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by pengjikun on 2018/3/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class MyTest {

    @Autowired
    private TestService testService;

    @Autowired
    private CouchbaseClient couchbaseClient;


    @Test
    public void get(){
        Object obj = testService.get(6);
        System.out.println(obj);
    }

    @Test
    public void couchbaseTest(){
        String key = "System:WechatUserInfo:o-Ca7jm22i23B2ZFeEahSyh0pVqA";
        Object obj = couchbaseClient.get(key);
        System.out.println(obj);
    }



}
