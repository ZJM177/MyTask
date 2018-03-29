package com.sun.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.couchbase.client.CouchbaseClient;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.task.dao.FanMigrateTempDao;
import com.sun.task.dto.FanMigrateResult;
import com.sun.task.service.WxService;
import com.sun.task.util.OkHttpUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by pengjikun on 2018/3/27.
 */
@Service
@Log4j
public class WxServiceImpl implements WxService {
    @Autowired
    private OkHttpUtil okHttpUtil;
    @Autowired
    private FanMigrateTempDao fanMigrateTempDao;
    @Autowired
    private CouchbaseClient couchbaseClient;

    public static final String CHANGE_OPENID_URL = "http://api.weixin.qq.com/cgi-bin/changeopenid?access_token=";
    public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    public static final String APPID_ACCESS_TOKEN = "AccessToken:%s";

    @Override
    @Async
    public void transferOpenIdApi(String oldAppId, String newAppId, String newSecret, int currentStart, int limit) {
        List<String> openIdList = queryByAppIdInLimit(oldAppId, currentStart, limit);
        if(CollectionUtils.isEmpty(openIdList)){
            return;
        }
        List<FanMigrateResult> tempResult = Lists.newArrayList();
        int lastStart = 0;
        for (int i = 0; i < openIdList.size(); i += 100) {
            //最后一次循环
            if ((i + 100) >= openIdList.size()) {
                List<String> sendOpenIdList = openIdList.subList(i, openIdList.size());
                List<FanMigrateResult> fanMigrateResultList = getNewOpenId(oldAppId, newAppId, newSecret, sendOpenIdList);
                tempResult.addAll(fanMigrateResultList);
            } else {
                List<String> sendOpenIdList = openIdList.subList(i, i + 100);
                List<FanMigrateResult> fanMigrateResultList = getNewOpenId(oldAppId, newAppId, newSecret, sendOpenIdList);
                tempResult.addAll(fanMigrateResultList);
            }

            //累计到1000条时或最后一次时，批量更新
            if(tempResult.size() >= 1000 || (i + 100) >= openIdList.size()){
                log.info(String.format(">>>正在批量更新，本次范围：%s ~ %s", lastStart==0?currentStart:lastStart, currentStart + i + 100));
                lastStart = currentStart + i + 100;
                fanMigrateTempDao.updateBatch(oldAppId, tempResult);
                tempResult.clear();
            }
        }
    }

    @Override
    @Async
    public void run(final int i){
        System.out.println("线程名：" + Thread.currentThread().getName() + ">>>序号：" + i);
    }

    @Override
    public int queryCount(String oldAppId) {
        return fanMigrateTempDao.queryCount(oldAppId);
    }

    @Override
    public int queryCountLimit(String oldAppId, int start, int limit) {
        return fanMigrateTempDao.queryCountLimit(oldAppId, start, limit);
    }

    @Override
    public String saveAccessToken(String newAppId, String accessToken) {
        couchbaseClient.set(String.format(APPID_ACCESS_TOKEN, newAppId), 5400, accessToken);
        return "AccessToken保存成功！";
    }

    @Override
    public String getAccessToken(String newAppId, String newSecret) {
        String accessToken = (String) couchbaseClient.get(String.format(APPID_ACCESS_TOKEN, newAppId));
        if(StringUtils.isEmpty(accessToken)){
            //锁控制
            synchronized (this.getClass()){
                accessToken = (String) couchbaseClient.get(String.format(APPID_ACCESS_TOKEN, newAppId));
                if(StringUtils.isEmpty(accessToken)){
                    accessToken = getTokenApi(newAppId, newSecret);
                    if(StringUtils.isEmpty(accessToken)){
                        throw new RuntimeException("获取AccessToken为空");
                    }
                    //保存
                    saveAccessToken(newAppId, accessToken);
                }
            }
        }
        return accessToken;
    }

    /**
     * 根据旧openId换取新openId
     * @param oldAppId
     * @param newAppId
     * @param openIdList
     * @return
     */
    private List<FanMigrateResult> getNewOpenId(String oldAppId, String newAppId, String newSecret, List<String> openIdList){
        Map<String, Object> reqMap = Maps.newHashMap();
        reqMap.put("from_appid", oldAppId);
        reqMap.put("openid_list", openIdList);
        String authAccessToken = getAccessToken(newAppId, newSecret);

        Map<String, Object> map = okHttpUtil.requestAuthWechatAPI(authAccessToken, CHANGE_OPENID_URL, reqMap, "POST");
        List<FanMigrateResult> result_list = JSON.parseArray(Objects.toString(map.get("result_list")), FanMigrateResult.class);
        return result_list;
    }

    private List<FanMigrateResult> getNewOpenId_test(List<String> openIdList){
        List<FanMigrateResult> result_list = Lists.newArrayList();
        FanMigrateResult result;
        for (String openId : openIdList){
            result = FanMigrateResult.builder().ori_openid(openId).new_openid(openId).build();
            result_list.add(result);
        }
        return result_list;
    }

    /**
     * 分页查旧openId
     * @param appId
     * @param start
     * @param limit
     * @return
     */
    private List<String> queryByAppIdInLimit(String appId, int start, int limit) {
        List<String> fanMigrateTemps = fanMigrateTempDao.queryByAppId(appId, start, limit);
        if(CollectionUtils.isEmpty(fanMigrateTemps)){
            return Lists.newArrayList();
        }
        return fanMigrateTemps;
    }

    private String getTokenApi(String appId, String secret){
        StringBuffer sb = new StringBuffer();
        String url = sb.append(GET_TOKEN_URL)
                .append("?grant_type=client_credential&appid=")
                .append(appId)
                .append("&secret=")
                .append(secret).toString();
        Map<String, Object> map = okHttpUtil.requestByClient(url, "GET", null);
        if(!CollectionUtils.isEmpty(map)){
            return String.valueOf(map.get("access_token"));
        }
        return null;
    }

}
