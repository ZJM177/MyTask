package com.sun.task.service;

/**
 * Created by pengjikun on 2018/3/27.
 */
public interface WxService {
    /**
     * 异步方法
     * @param oldAppId
     * @param newAppId
     * @param start
     * @param limit
     */
    void transferOpenIdApi(String thread, String oldAppId, String newAppId, String newSecret, int start, int limit);

    void run(int i);

    int queryCount(String oldAppId);

    int queryCountLimit(String oldAppId, int start, int limit);

    String saveAccessToken(String newAppId,  String accessToken);

    String getAccessToken(String newAppId, String newSecret);

}
