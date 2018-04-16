package com.sun.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.couchbase.client.CouchbaseClient;
import com.google.common.collect.Lists;
import com.sun.task.dao.OpenIdMapingDao;
import com.sun.task.dto.OpenIdMaping;
import com.sun.task.dto.WechatUserInfo;
import com.sun.task.service.TransferOpenIdService;
import com.sun.task.util.CommonUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pengjikun on 2017/2/15.
 */
@Service
@Log4j
public class TransferOpenIdServiceImpl implements TransferOpenIdService {
    @Autowired
    private OpenIdMapingDao openIdMapingDao;
    @Autowired
    private CouchbaseClient couchbaseClient;

    @Value("${custom.point.levels}")
    private String pointLevels;
    @Value("${custom.book.preSubCampCode}")
    private String preSubCampCode;
    @Value("${custom.kf.days}")
    private int kfDays;

    /**存放新老openId对应关系*/
    private static Map<String, String> openIdMapingData = new ConcurrentHashMap<>();

    private final static String USER_PRE = "System:WechatUserInfo:";
    private final static String POINT_PRE = "1:SubCampIfJoin:";

    @Override
    public String transferFansOpenId(int start, int limit) {
        StringBuffer oldBuffer = new StringBuffer();
        StringBuffer newBuffer = new StringBuffer();
        List<OpenIdMaping> openIdMapingList = this.getOpenIdMapingList(start, limit);
        for (OpenIdMaping openIdMaping : openIdMapingList) {
            int No = openIdMaping.getNo();
            String oldOpenId = openIdMaping.getOldOpenId();
            oldBuffer.setLength(0);
            String oldKey = oldBuffer.append(USER_PRE).append(oldOpenId).toString();
            String str = (String) couchbaseClient.get(oldKey);
            if (StringUtils.isEmpty(str)) {
                log.info(String.format("查询粉丝记录为空，将忽略此记录，openId：%s，排序值No：%s", oldOpenId, No));
                continue;
            }
            JSONObject jsonObject = JSON.parseObject(str);

            String newOpenId = openIdMaping.getNewOpenId();
            newBuffer.setLength(0);
            String newKey = newBuffer.append(USER_PRE).append(newOpenId).toString();
            //替换openId
            jsonObject.put("openId", openIdMaping.getNewOpenId());
            String wechatUserInfoStr = JSON.toJSONString(jsonObject);
            //插入新数据
            couchbaseClient.set(newKey, wechatUserInfoStr);
            couchbaseClient.delete(oldKey);
            log.info(String.format("转换粉丝成功>>>，oldOpenId：%s，newOpenId：%s，排序值No：%s", oldOpenId, newOpenId, No));
        }
        return "转换粉丝成功";
    }

    @Override
    public String getByOpenId(String openId) {
        return (String) couchbaseClient.get(USER_PRE + openId);
    }

    @Override
    public boolean compareByOpenId(String oldOpenId, String newOpenId) {
        String oldData = getByOpenId(oldOpenId);
        String newData = getByOpenId(newOpenId);
        WechatUserInfo oldUser = JSON.parseObject(oldData, WechatUserInfo.class);
        WechatUserInfo newUser = JSON.parseObject(newData, WechatUserInfo.class);
        return this.compare(oldUser, newUser);
    }

    @Override
    public String transferPointOpenId(int start, int limit) {
        List<String> levels = JSON.parseArray(pointLevels, String.class);
        if(CollectionUtils.isEmpty(levels)){
            String format = String.format("积分档次配置解析后为空，配置：%s，解析后：%s", pointLevels, levels);
            log.info(format);
            return format;
        }

        List<OpenIdMaping> openIdMapingList = this.getOpenIdMapingList(start, limit);
        StringBuffer oldBuffer = new StringBuffer();
        StringBuffer newBuffer = new StringBuffer();
        for (OpenIdMaping openIdMaping : openIdMapingList) {
            int No = openIdMaping.getNo();
            String oldOpenId = openIdMaping.getOldOpenId();
            for (String level : levels) {
                oldBuffer.setLength(0);
                String oldKey = oldBuffer.append(POINT_PRE).append(oldOpenId).append(":").append(level).toString();
                Object obj = couchbaseClient.get(oldKey);
                if(null == obj){
                    log.info(String.format("查询积分兑换记录为空，将忽略此记录，openId：%s，积分档次：%s，排序值No：%s", oldOpenId, level, No));
                    continue;
                }
                newBuffer.setLength(0);
                String newOpenId = openIdMaping.getNewOpenId();
                String newKey = newBuffer.append(POINT_PRE).append(newOpenId).append(":").append(level).toString();
                couchbaseClient.set(newKey, 1);
                log.info(String.format("转换积分兑换标记成功>>>，oldOpenId：%s，newOpenId：%s，积分档次：%s，排序值No：%s", oldOpenId, newOpenId, level, No));
            }
        }
        return "转换积分兑换标记成功";
    }

    @Override
    public String transferBookOpenId(int start, int limit) {
        /**
         * avene docId格式 1:SubCampInfo:AS1512290000002:oPXa4uH6byCLVneO03FJwWPbnnQg
         * rf docId格式 1:SubCampOrder:AT1605110000001oCZUluNTz8sVqbsHRy61XG0Uo8hQ
         */
        StringBuffer oldBuffer = new StringBuffer();
        StringBuffer newBuffer = new StringBuffer();
        List<OpenIdMaping> openIdMapingList = this.getOpenIdMapingList(start, limit);
        for (OpenIdMaping openIdMaping : openIdMapingList) {
            int No = openIdMaping.getNo();
            String oldOpenId = openIdMaping.getOldOpenId();
            oldBuffer.setLength(0);
            String oldKey = oldBuffer.append(preSubCampCode).append(oldOpenId).toString();
            Object obj = couchbaseClient.get(oldKey);
            if(null == obj){
                log.info(String.format("查询预约记录为空，将忽略此记录，openId：%s，排序值No：%s", oldOpenId, No));
                continue;
            }
            newBuffer.setLength(0);
            String newOpenId = openIdMaping.getNewOpenId();
            String newKey = newBuffer.append(preSubCampCode).append(newOpenId).toString();
            couchbaseClient.set(newKey, obj);
            couchbaseClient.delete(oldKey);
            log.info(String.format("转换预约记录成功>>>，oldOpenId：%s，newOpenId：%s，排序值No：%s", oldOpenId, newOpenId, No));
        }
        return "转换预约记录成功！";
    }

    @Override
    public String transferKfOpenId(Date startDate, int count) {
        if(startDate == null){
            startDate = new Date();
        }
        if(count == 0){
            count = kfDays;
        }
        StringBuffer stf = new StringBuffer();
        String str;
        for (int i = 0; i <= count; i++) {
            Date addDateByDays = CommonUtil.addDateByDays(startDate, -i);
            String date2String = CommonUtil.date2String(addDateByDays, "yyyy-MM-dd");
            int j = 0;
            do {
                j += 1;
                stf.setLength(0);
                String key = stf.append("1:WeChatQaMain:")
                        .append(date2String)
                        .append(":")
                        .append(j)
                        .toString();
                str = (String) couchbaseClient.get(key);
                if (!StringUtils.isEmpty(str)) {
                    JSONObject jsonObject = JSON.parseObject(str);
                    String oldOpenId = jsonObject.getString("openId");
                    String newOpenId = getNewOpenIdByOldOpenId(oldOpenId);
                    if (StringUtils.isEmpty(newOpenId)) {
                        log.info(String.format("查询openId对应关系为空，将忽略此记录，openId：%s，日期：%s, 当天序号：%s",
                                oldOpenId, date2String, j));
                        continue;
                    }
                    jsonObject.put("openId", newOpenId);
                    String jsonString = JSON.toJSONString(jsonObject);
                    couchbaseClient.set(key, jsonString);
                    log.info(String.format("转换客服聊天记录成功>>>，oldOpenId：%s，newOpenId：%s，日期：%s, 当天序号：%s",
                            oldOpenId, newOpenId, date2String, j));
                }
            } while (str != null);
        }
        return "更新客服数据成功";
    }

    private boolean compare(WechatUserInfo oldUser, WechatUserInfo newUser) {
        if (oldUser == null || newUser == null) {
            log.info("对象为空");
            return false;
        }
        if (StringUtils.isEmpty(oldUser.getOpenId()) || StringUtils.isEmpty(newUser.getOpenId())
                || oldUser.getOpenId().equals(newUser.getOpenId())) {
            log.info(String.format("openId为空或两个对象的openId相等,oldOpenId:%s，newOpenId:%s", oldUser.getOpenId(), newUser.getOpenId()));
            return false;
        }
        return oldUser.toString().equals(newUser.toString());
    }

    /**
     * 获取openId对应关系
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<OpenIdMaping> getOpenIdMapingList(int start, int limit){
        int end = start + limit;
        List<OpenIdMaping> openIdMapings = openIdMapingDao.queryList(start, end);
        if (CollectionUtils.isEmpty(openIdMapings)) {
            return Lists.newArrayList();
        }
        return openIdMapings;
    }

    /**
     * 获取所有对应关系
     * @return
     */
    public List<OpenIdMaping> getOpenIdMapingList(){
        List<OpenIdMaping> openIdMapings = openIdMapingDao.queryListAll();
        if (CollectionUtils.isEmpty(openIdMapings)) {
            return Lists.newArrayList();
        }
        return openIdMapings;
    }

    /**
     * 获取对应关系
     * @param oldOpenId
     * @return
     */
    public String getNewOpenIdByOldOpenId(String oldOpenId){
        if(CollectionUtils.isEmpty(openIdMapingData)){
            synchronized (this.getClass()){
                if(CollectionUtils.isEmpty(openIdMapingData)){
                    List<OpenIdMaping> openIdMapingList = getOpenIdMapingList();
                    for (OpenIdMaping openIdMaping : openIdMapingList) {
                        this.openIdMapingData.put(openIdMaping.getOldOpenId(), openIdMaping.getNewOpenId());
                    }
                }
            }
        }
        return openIdMapingData.get(oldOpenId);
    }



}
