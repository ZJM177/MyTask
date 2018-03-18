package com.sun.task;

import com.alibaba.fastjson.JSON;
import com.couchbase.client.CouchbaseClient;
import com.sun.task.dto.WechatUserInfo;
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
public class CouchbaseTest {

    @Autowired
    private CouchbaseClient couchbaseClient;

    private final static String USER_PRE = "System:WechatUserInfo:";


    @Test
    public void CouchbaseClientTest(){
        String key = USER_PRE + "001";
        String str = (String) couchbaseClient.get(key);
        WechatUserInfo wechatUserInfo = JSON.parseObject(str, WechatUserInfo.class);
        System.out.println(wechatUserInfo);

        String newOpenId = "new001";
        wechatUserInfo.setOpenId(newOpenId);
        String jsonString = JSON.toJSONString(wechatUserInfo);
        String newKey = USER_PRE + newOpenId;
        couchbaseClient.set(newKey, jsonString);

        str = (String) couchbaseClient.get(newKey);
        wechatUserInfo = JSON.parseObject(str, WechatUserInfo.class);
        System.out.println(wechatUserInfo);
    }

    @Test
    public void test(){
        String key = USER_PRE + "001";
        String str = (String) couchbaseClient.get(key);
        WechatUserInfo wechatUserInfo = JSON.parseObject(str, WechatUserInfo.class);

        for (int i = 0; i < 100; i++) {
            String newOpenId = "new" + i;
            wechatUserInfo.setOpenId(newOpenId);
            String jsonString = JSON.toJSONString(wechatUserInfo);
            String newKey = USER_PRE + newOpenId;
            couchbaseClient.set(newKey, jsonString);
        }

    }



}
