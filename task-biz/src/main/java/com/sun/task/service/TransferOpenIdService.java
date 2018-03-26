package com.sun.task.service;

import com.sun.task.dto.OpenIdMaping;

import java.util.Date;
import java.util.List;

/**
 * Created by pengjikun on 2017/2/15.
 */
public interface TransferOpenIdService {

    /**
     * 转换粉丝openId
     * @param start
     * @param limit
     */
    String transferFansOpenId(int start, int limit);

    /**
     * 获取粉丝信息
     * @param openId
     * @return
     */
    String getByOpenId(String openId);

    /**
     * 转换粉丝后对比
     * @param oldOpenId
     * @param newOpenId
     * @return
     */
    boolean compareByOpenId(String oldOpenId, String newOpenId);

    /**
     * 转换积分兑换记录openId
     * @param start
     * @param limit
     * @return
     */
    String transferPointOpenId(int start, int limit);

    /**
     * 转换预约记录openId
     * @param type 区分雅漾和RF
     * @param start
     * @param limit
     * @return
     */
    String transferBookOpenId(int type, int start, int limit);

    /**
     * 转换客服交流明细openId
     * @param startDate 从该日期往之前开始，若空则默认当天
     * @param count 查询每天的基数，若空则默认配置
     * @return
     */
    String transferKfOpenId(Date startDate, int count);

    /**
     * 分页取对应关系
     * @param start
     * @param limit
     * @return
     */
    List<OpenIdMaping> getOpenIdMapingList(int start, int limit);

    /**
     * 取所有对应关系
     * @return
     */
    List<OpenIdMaping> getOpenIdMapingList();

    /**
     * 根据旧openId取得新openId
     * @param oldOpenId
     * @return
     */
    String getNewOpenIdByOldOpenId(String oldOpenId);

}
