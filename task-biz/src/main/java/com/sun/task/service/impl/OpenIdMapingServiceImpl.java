package com.sun.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.couchbase.client.CouchbaseClient;
import com.google.common.collect.Lists;
import com.sun.task.dao.OpenIdMapingDao;
import com.sun.task.dto.OpenIdMaping;
import com.sun.task.dto.WechatUserInfo;
import com.sun.task.enums.TypeEnum;
import com.sun.task.service.OpenIdMapingService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
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

    @Value("${custom.point.rf.levels}")
    private String pointRfLevels;
    @Value("${custom.point.avene.levels}")
    private String pointAveneLevels;
    @Value("${custom.book.rf.subCampCode}")
    private String rfSubCampCode;
    @Value("${custom.book.avene.subCampCode}")
    private String aveneSubCampCode;

    private final static String USER_PRE = "System:WechatUserInfo:";
    private final static String POINT_PRE = "1:SubCampIfJoin:";
    private final static String RF_BOOK_PRE = "1:SubCampOrder:";
    private final static String AVENE_BOOK_PRE = "1:SubCampInfo:";

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
            WechatUserInfo wechatUserInfo = JSON.parseObject(str, WechatUserInfo.class);

            String newOpenId = openIdMaping.getNewOpenId();
            newBuffer.setLength(0);
            String newKey = newBuffer.append(USER_PRE).append(newOpenId).toString();
            //替换openId
            wechatUserInfo.setOpenId(openIdMaping.getNewOpenId());
            String wechatUserInfoStr = JSON.toJSONString(wechatUserInfo);
            //插入新数据
            couchbaseClient.set(newKey, wechatUserInfoStr);
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
    public String transferPointOpenId(int type, int start, int limit) {
        String levelStr = (type == TypeEnum.AVENE.getType())?pointAveneLevels: pointRfLevels;
        List<String> levels = JSON.parseArray(levelStr, String.class);
        if(CollectionUtils.isEmpty(levels)){
            String format = String.format("积分档次配置解析后为空，配置：%s，解析后：%s", levelStr, levels);
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
    public String transferBookOpenId(int type, int start, int limit) {
        /**
         * avene docId格式 1:SubCampInfo:AS1512290000002:oPXa4uH6byCLVneO03FJwWPbnnQg
         * rf docId格式 1:SubCampOrder:AT1605110000001oCZUluNTz8sVqbsHRy61XG0Uo8hQ
         */
        String bookPre = (type==TypeEnum.AVENE.getType())?AVENE_BOOK_PRE:RF_BOOK_PRE;
        String subCampCode = (type==TypeEnum.AVENE.getType())?aveneSubCampCode+":":rfSubCampCode;

        StringBuffer oldBuffer = new StringBuffer();
        StringBuffer newBuffer = new StringBuffer();
        List<OpenIdMaping> openIdMapingList = this.getOpenIdMapingList(start, limit);
        for (OpenIdMaping openIdMaping : openIdMapingList) {
            int No = openIdMaping.getNo();
            String oldOpenId = openIdMaping.getOldOpenId();
            oldBuffer.setLength(0);
            String oldKey = oldBuffer.append(bookPre).append(subCampCode).append(oldOpenId).toString();
            Object obj = couchbaseClient.get(oldKey);
            if(null == obj){
                log.info(String.format("查询预约记录为空，将忽略此记录，openId：%s，排序值No：%s", oldOpenId, No));
                continue;
            }
            newBuffer.setLength(0);
            String newOpenId = openIdMaping.getNewOpenId();
            String newKey = newBuffer.append(bookPre).append(subCampCode).append(newOpenId).toString();
            couchbaseClient.set(newKey, obj);
            log.info(String.format("转换预约记录成功>>>，oldOpenId：%s，newOpenId：%s，排序值No：%s", oldOpenId, newOpenId, No));
        }
        return "转换预约记录成功！";
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
    private List<OpenIdMaping> getOpenIdMapingList(int start, int limit){
        int end = start + limit;
        List<OpenIdMaping> openIdMapingList = openIdMapingDao.queryList(start, end);
        if (CollectionUtils.isEmpty(openIdMapingList)) {
            return Lists.newArrayList();
        }
        return openIdMapingList;
    }



}
