package com.sun.task.job;

import lombok.extern.log4j.Log4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by pengjikun on 2018/4/7.
 * 主要分两种方式设置定时job，fixedRate指定每个固定时间执行一次，cron指定按照表达式时间执行。
 */
@Component
@Log4j
public class TestJob {

    /**
     * 启动后延迟1秒执行，之后每隔1小时执行一次
     */
    @Scheduled(initialDelay=1000, fixedRate=3600000)
    public void testJob(){
        log.info(">>>执行testJob");
    }

    /**
     * 每5秒执行一次，不支持initialDelay属性
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void testJob2(){
        log.info(">>>执行testJob2开始");
    }
}
