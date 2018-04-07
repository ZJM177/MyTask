package com.sun.task.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.sun.task.service.TestService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by pengjikun on 2017/2/15.
 */
@Controller
@Log4j
public class SampleController {

    @Autowired
    private TestService service;

    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello world!";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get(@RequestParam("id") int id) {
        return service.get(id);
    }

    //测试session共享，用两个不同端口启动该服务，然后在同一个浏览器上访问该url即可,验证：是否会返回相同的sessionId？
    @RequestMapping("/sessions")
    @ResponseBody
    public String sessions(HttpServletRequest request) {
        request.getSession().setAttribute("url", request.getRequestURL().toString());
        Map<String, Object> map = Maps.newHashMap();
        map.put("url", request.getSession().getAttribute("url"));
        map.put("sessionId", request.getSession().getId());
        return JSON.toJSONString(map);
    }

}
