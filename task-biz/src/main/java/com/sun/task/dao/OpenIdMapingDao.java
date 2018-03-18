package com.sun.task.dao;

import com.sun.task.dto.OpenIdMaping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OpenIdMapingDao {
    List<OpenIdMaping> queryList(@Param("status")int status, @Param("start")int start,
                                 @Param("limit")int limit);

    void batchInsert(@Param("openIdMapings") List<OpenIdMaping> openIdMapings);
}