package com.sun.task.service;

import com.sun.task.dto.OpenIdMaping;

import java.util.List;

/**
 * Created by pengjikun on 2017/2/15.
 */
public interface OpenIdMapingService {

    void changeOpenId(int start, int limit);

    void batchInsert(List<OpenIdMaping> openIdMapings);

}
