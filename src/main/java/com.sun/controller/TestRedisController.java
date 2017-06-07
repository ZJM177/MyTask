package com.sun.controller;

import com.sun.service.TestRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pengjikun on 2017/2/18.
 */
@Controller
public class TestRedisController {

    @Autowired
    private TestRedisService testService;

    @RequestMapping("/test")
    @ResponseBody
    String test() {
        return testService.test();
    }

    @RequestMapping("/testCache")
    @ResponseBody
    String testCache() {
        testService.setValue("test111");
        return testService.getValue();
    }
}
