package com.sun.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.couchbase.client.CouchbaseClient;
import com.sun.task.dao.OpenIdMapingDao;
import com.sun.task.dto.OpenIdMaping;
import com.sun.task.dto.WechatUserInfo;
import com.sun.task.service.OpenIdMapingService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by pengjikun on 2017/2/15.
 */
@Service
@Log4j
public class OpenIdMapingServiceImpl implements OpenIdMapingService {
    @Autowired
    private OpenIdMapingDao openIdMapingDao;
    @Autowired
    private CouchbaseClient couchbaseClient;

    private final static String USER_PRE = "System:WechatUserInfo:";


    @Override
    public void changeOpenId(int start, int limit) {
        int end = start + limit;
        List<OpenIdMaping> openIdMapingList = openIdMapingDao.queryList(start, end);
        if (CollectionUtils.isEmpty(openIdMapingList)) {
            return;
        }

        StringBuffer oldBuffer = new StringBuffer();
        StringBuffer newBuffer = new StringBuffer();
        for (OpenIdMaping openIdMaping : openIdMapingList) {
            int No = openIdMaping.getNo();
            String oldOpenId = openIdMaping.getOldOpenId();
            oldBuffer.setLength(0);
            String oldKey = oldBuffer.append(USER_PRE).append(oldOpenId).toString();
            String str = (String) couchbaseClient.get(oldKey);
            if(StringUtils.isEmpty(str)){
                log.info(String.format("根据openId：%s查询记录为空，将忽略此记录，排序值No：%s", oldOpenId, No));
                continue;
            }
            WechatUserInfo wechatUserInfo = JSON.parseObject(str, WechatUserInfo.class);

            String newOpenId = openIdMaping.getNewOpenId();
            newBuffer.setLength(0);
            String newKey = newBuffer.append(USER_PRE).append(newOpenId).toString();
            //替换openId
            wechatUserInfo.setOpenId(openIdMaping.getNewOpenId());
            String wechatUserInfoStr = JSON.toJSONString(wechatUserInfo);
            //插入新数据
            couchbaseClient.set(newKey, wechatUserInfoStr);
            log.info(String.format("刷新一条成功，oldOpenId：%s，newOpenId：%s，排序值No：%s", oldOpenId, newOpenId, No));
        }
    }

    @Override
    public void batchInsert(List<OpenIdMaping> openIdMapings) {
        openIdMapingDao.batchInsert(openIdMapings);
    }


}
