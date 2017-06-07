package com.sun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pengjikun on 2017/2/15.
 */
@Controller
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String hello() {
        return "Hello World2!";
    }



}
