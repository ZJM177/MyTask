package com.sun.task.controller;

import com.alibaba.fastjson.JSON;
import com.sun.task.service.OpenIdMapingService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
            return "本次失败，limit参数必须是正整数";
        }
        openIdMapingService.changeOpenId(start, limit);
        return "刷新成功!";
    }

    @RequestMapping("/getByOpenId")
    @ResponseBody
    public String getByOpenId(@RequestParam("openId")String openId) {
        if(StringUtils.isEmpty(openId)){
            return "openId不允许为空";
        }
        String result = openIdMapingService.getByOpenId(openId);
        return "获取完成，结果是：" + result;
    }

    @RequestMapping("/compareByOpenId")
    @ResponseBody
    public String compareByOpenId(@RequestParam("oldOpenId")String oldOpenId,
                                  @RequestParam("newOpenId")String newOpenId) {
        if(StringUtils.isEmpty(oldOpenId) || StringUtils.isEmpty(newOpenId)){
            return "oldOpenId和newOpenId都不允许为空";
        }
        boolean compareResult = openIdMapingService.compareByOpenId(oldOpenId, newOpenId);
        return "比较完成，结果是：" + compareResult;
    }

}
