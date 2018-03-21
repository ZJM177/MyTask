package com.sun.task.service;

import com.sun.task.dto.OpenIdMaping;

import java.util.List;

/**
 * Created by pengjikun on 2017/2/15.
 */
public interface OpenIdMapingService {

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
     * @param type 区分雅漾和RF
     * @param start
     * @param limit
     * @return
     */
    String transferPointOpenId(int type, int start, int limit);

    /**
     * 转换预约记录openId
     * @param type 区分雅漾和RF
     * @param start
     * @param limit
     * @return
     */
    String transferBookOpenId(int type, int start, int limit);

}
