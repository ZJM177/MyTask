package com.sun.task.controller;

import com.sun.task.service.TransferOpenIdService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pengjikun on 2017/2/15.
 */
@Controller
@RequestMapping("/task")
@Log4j
public class TransferOpenIdController {

    @Autowired
    private TransferOpenIdService transferOpenIdService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/transferFansOpenId")
    @ResponseBody
    public String transferFansOpenId(@RequestParam("start") int start,
                               @RequestParam("limit") int limit) {
        if (limit == 0) {
            return "本次失败，limit参数必须是正整数";
        }
        return transferOpenIdService.transferFansOpenId(start, limit);
    }

    @RequestMapping("/getByOpenId")
    @ResponseBody
    public String getByOpenId(@RequestParam("openId") String openId) {
        if (StringUtils.isEmpty(openId)) {
            return "openId不允许为空";
        }
        String result = transferOpenIdService.getByOpenId(openId);
        return "获取完成，\n 结果是：" + result;
    }

    @RequestMapping("/compareByOpenId")
    @ResponseBody
    public String compareByOpenId(@RequestParam("oldOpenId") String oldOpenId,
                                  @RequestParam("newOpenId") String newOpenId) {
        if (StringUtils.isEmpty(oldOpenId) || StringUtils.isEmpty(newOpenId)) {
            return "oldOpenId和newOpenId都不允许为空";
        }
        boolean compareResult = transferOpenIdService.compareByOpenId(oldOpenId, newOpenId);
        return "比较完成，\n 结果是：" + compareResult;
    }

    @RequestMapping("/transferPointOpenId")
    @ResponseBody
    public String transferPointOpenId(@RequestParam("start") int start,
                                     @RequestParam("limit") int limit) {
        if (limit == 0) {
            return "本次失败，limit参数必须是正整数";
        }
        return transferOpenIdService.transferPointOpenId(start, limit);
    }

    @RequestMapping("/transferBookOpenId")
    @ResponseBody
    public String transferBookOpenId(@RequestParam("type") int type,
                                      @RequestParam("start") int start,
                                      @RequestParam("limit") int limit) {
        if (limit == 0) {
            return "本次失败，limit参数必须是正整数";
        }
        return transferOpenIdService.transferBookOpenId(type, start, limit);
    }

    @RequestMapping("/transferKfOpenId")
    @ResponseBody
    public String transferKfOpenId() {
        return transferOpenIdService.transferKfOpenId();
    }




}
