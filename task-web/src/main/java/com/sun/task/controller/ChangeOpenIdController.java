package com.sun.task.controller;

import com.sun.task.service.OpenIdMapingService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengjikun on 2017/2/15.
 */
@RestController
@RequestMapping("/task")
@Log4j
public class ChangeOpenIdController {

    @Autowired
    private OpenIdMapingService openIdMapingService;

    @RequestMapping("/changeOpenId")
    @ResponseBody
    public String changeOpenId(@RequestParam("start")int start,
                               @RequestParam("limit")int limit) {
        if(limit == 0){
            start = 0;
            limit = Integer.MAX_VALUE;
        }
        openIdMapingService.changeOpenId(start, limit);
        return "刷新成功!";
    }


}
