package com.sun.task.dao;

import com.sun.task.dto.FanMigrateResult;
import com.sun.task.dto.FanMigrateTemp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by tubangwu on 2018/3/15.
 *
 * @author tubangwu
 */
public interface FanMigrateTempDao{


    List<String> queryByAppId(@Param("appId") String appId, @Param("start") int start, @Param("limit") int limit);


    void updateByAppIdAndOpenId(@Param("appId") String appId, @Param("openId") String openId, @Param("newOpenId") String newOpenId);

    void updateStatus(@Param("appId") String appId, @Param("openId") String openId);

    int queryCount(@Param("appId") String appId);

    int queryCountLimit(@Param("appId") String appId, @Param("start") int start, @Param("limit") int limit);

    void updateBatch(@Param("appId") String appId, @Param("list") List<FanMigrateResult> openIdList);

}
