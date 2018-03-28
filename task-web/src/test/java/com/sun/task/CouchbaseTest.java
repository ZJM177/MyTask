package com.sun.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.couchbase.client.CouchbaseClient;
import com.sun.task.dto.WechatUserInfo;
import com.sun.task.util.CommonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Created by pengjikun on 2018/3/16.
 */
public class CouchbaseTest extends BaseTest{

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
    public void testInsert() throws InterruptedException {
        String key = USER_PRE + "001";
        String str = (String) couchbaseClient.get(key);
        WechatUserInfo wechatUserInfo = JSON.parseObject(str, WechatUserInfo.class);

        for (int i = 1; i <= 100; i++) {
            String newOpenId = "old" + i;
            wechatUserInfo.setOpenId(newOpenId);
            String jsonString = JSON.toJSONString(wechatUserInfo);
            String newKey = USER_PRE + newOpenId;
            couchbaseClient.set(newKey, jsonString);
            Thread.sleep(100L);
        }
    }

    @Test
    public void testDel(){
//        for (int i = 1; i <= 100; i++) {
//            String openId = "old" + i;
//            String key = USER_PRE + openId;
//            couchbaseClient.delete(key);
//        }
        couchbaseClient.delete(USER_PRE + "new0");
    }

    @Test
    public void testKfDetail(){
        Date date = new Date();
        StringBuffer stf = new StringBuffer();
        String jsonStr;
        for (int i = 0; i < 1; i++) {
            Date addDateByDays = CommonUtil.addDateByDays(date, -i);
            String date2String = CommonUtil.date2String(addDateByDays, "yyyy-MM-dd");
            int j = 0;
            do {
                j += 1;
                stf.setLength(0);
                String key = stf
                        .append("1:WeChatQaMain:")
                        .append(date2String)
                        .append(":")
                        .append(j)
                        .toString();
                jsonStr = (String)couchbaseClient.get(key);
                if(!StringUtils.isEmpty(jsonStr)){
                    JSONObject jsonObject = JSON.parseObject(jsonStr);
//                    String openId = jsonObject.getString("openId");
//                    if(!StringUtils.isEmpty(openId) && !"null".equals(openId)){
//                        continue;
//                    }
                    jsonObject.put("openId", "oLURGs7Ii0lwWvt6DV_qE2QODO24");
                    String jsonString = JSON.toJSONString(jsonObject);
                    couchbaseClient.set(key, jsonString);
                }
            }while (jsonStr != null);
        }
    }


}
