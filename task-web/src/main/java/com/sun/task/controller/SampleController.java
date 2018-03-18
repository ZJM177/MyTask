package com.sun.task.controller;

import com.sun.task.service.OpenIdMapingService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pengjikun on 2017/2/15.
 */
@Controller
@Log4j
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello world!";
    }


}