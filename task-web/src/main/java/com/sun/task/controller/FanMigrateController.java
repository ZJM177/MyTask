package com.sun.task.controller;

import com.sun.task.service.WxService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pengjikun on 2017/2/15.
 */
@Controller
@Log4j
public class FanMigrateController {
    @Autowired
    private WxService wxService;

    @Value("${custom.perCount}")
    private int perCount;
    @Value("${custom.oldAppId}")
    private String oldAppId;
    @Value("${custom.newAppId}")
    private String newAppId;
    @Value("${custom.newSecret}")
    private String newSecret;

    @RequestMapping("/doTransfer")
    @ResponseBody
    public String doTransfer(@RequestParam("start") int start,
                             @RequestParam("limit") int limit) {

        int totalCount = wxService.queryCount(oldAppId);
        log.info(String.format("总共有条数：%s", totalCount));
        if(start >= totalCount){
            return "执行失败，start大于总数";
        }
        int countLimit = wxService.queryCountLimit(oldAppId, start, limit);
        if(countLimit < limit){
            limit = countLimit;
        }
        log.info(String.format("本次要执行条数：%s", limit));
        //一个线程最多处理数量，默认10000
        if(perCount == 0){
            perCount = 10000;
        }
        if(limit > perCount){
            int threadCount = limit / perCount;
            int restCount = limit % perCount;
            if (restCount != 0){//取余数不为0，要多开一个线程
                threadCount += 1;
            }
            log.info(String.format("启用线程数：%s", threadCount));
            int currentStart = start;
            for (int i = 1; i <= threadCount; i++) {
                int currentLimit = perCount;
                if(i == threadCount && restCount > 0){//最后一个线程有可能处理未满perCount个数
                    currentLimit = restCount;
                }
                log.info(String.format(">>>正在分配线程，序号：%s，处理范围%s ~ %s", i, currentStart, currentStart + currentLimit));
                wxService.transferOpenIdApi(oldAppId, newAppId, newSecret, currentStart, currentLimit);
                currentStart += currentLimit;
            }
        }else {
            //小于单个线程处理数的，直接启用一个线程执行
            log.info(String.format(">>>小于单个线程处理数，直接启用一个线程"));
            wxService.transferOpenIdApi(oldAppId, newAppId, newSecret, start, limit);
        }
        return String.format(">>>旧openId转换新openId任务建立成功，正在努力执行，>>>start：%s，limit：%s，", start, limit);
    }


    @RequestMapping("/saveAccessToken")
    @ResponseBody
    public String saveAccessToken(@RequestParam("accessToken") String accessToken){
        return wxService.saveAccessToken(newAppId, accessToken);
    }

    @RequestMapping("/getAccessToken")
    @ResponseBody
    public String getAccessToken(){
        return "结果：" + wxService.getAccessToken(newAppId, newSecret);
    }

}
