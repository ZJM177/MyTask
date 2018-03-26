package com.sun.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.couchbase.client.CouchbaseClient;
import com.sun.task.dao.OpenIdMapingDao;
import com.sun.task.dto.OpenIdMaping;
import com.sun.task.service.TransferOpenIdService;
import com.sun.task.util.CommonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by pengjikun on 2018/3/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class TransferOpenIdServiceTest {

    @Autowired
    private TransferOpenIdService transferOpenIdService;
    @Autowired
    private OpenIdMapingDao openIdMapingDao;

    @Autowired
    private CouchbaseClient couchbaseClient;

    @Test
    public void transferFansOpenId(){
        transferOpenIdService.transferFansOpenId(0, 10);
    }

    @Test
    public void batchInsert(){
        List<OpenIdMaping> openIdMapings = new ArrayList<>();
        OpenIdMaping openIdMaping;
        for (int i = 1; i <= 10; i++) {
            openIdMaping = OpenIdMaping.builder()
                    .oldOpenId("old"+i)
                    .newOpenId("new"+i)
                    .mobile("187"+i)
                    .No(i)
                    .build();
            openIdMapings.add(openIdMaping);
        }
        openIdMapingDao.batchInsert(openIdMapings);

    }


    @Test
    public void transferKfOpenId(){
        transferOpenIdService.transferKfOpenId();
    }

    @Test
    public void insertMockData() throws InterruptedException {
        String date2String = CommonUtil.date2String(new Date(), "yyyy-MM-dd");
        StringBuffer stf = new StringBuffer();
        String key = stf
                .append("1:WeChatQaMain:")
                .append(date2String)
                .append(":1")
                .toString();

        String jsonStr = (String)couchbaseClient.get(key);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        List<OpenIdMaping> openIdMapingList = transferOpenIdService.getOpenIdMapingList();
        for (int i = 48321; i < openIdMapingList.size(); i++) {
            OpenIdMaping openIdMaping = openIdMapingList.get(i);
            jsonObject.put("openId", openIdMaping.getOldOpenId());
            String jsonString = JSON.toJSONString(jsonObject);
            stf.setLength(0);
            String newKey = stf.append("1:WeChatQaMain:")
                    .append(date2String)
                    .append(":")
                    .append(i + 1)
                    .toString();
            couchbaseClient.set(newKey, jsonString);
            System.out.println(i);
        }
    }

    @Test
    public void queryMockData(){
        String date2String = CommonUtil.date2String(new Date(), "yyyy-MM-dd");
        StringBuffer stf = new StringBuffer();
        for (int i = 0; i < 50000; i++) {
            int c = i + 1;
            stf.setLength(0);
            String key = stf.append("1:WeChatQaMain:")
                    .append(date2String)
                    .append(":")
                    .append(c)
                    .toString();
            Object obj = couchbaseClient.get(key);
            if(obj == null){
                System.out.println(c);
            }
        }
    }

}
