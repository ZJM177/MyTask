package com.sun.task;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.task.dao.FanMigrateTempDao;
import com.sun.task.service.WxService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Map;

/**
 * Created by pengjikun on 2018/3/27.
 */
public class WxServiceTest extends BaseTest {
    @Autowired
    private WxService wxService;
    @Autowired
    private FanMigrateTempDao fanMigrateTempDao;

    @Test
    public void test(){
        String oldAppId = "wxfd91cd2453418fc0";
        int countLimit = fanMigrateTempDao.queryCountLimit(oldAppId, 100000, 10000);
        System.out.println(countLimit);
    }

    @Test
    public void testAsync(){
        for (int i = 0; i < 20; i++) {
            wxService.run(i);
        }
    }

    @Test
    public void testAsync2(){
        String oldAppId = "wxfd91cd2453418fc0";
        int poolSize = 20;
        int count = 10000;
        int start = 0;
        for (int i = 0; i < poolSize; i++) {
            wxService.transferOpenIdApi(oldAppId, "", "", start, count);
            start += count;
        }
    }



}
