package com.sun.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import java.util.Date;

/**
 * Created by tubangwu on 2018/3/15.
 *
 * @author tubangwu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FanMigrateTemp{
    private int id;
    private Date createTime;
    private Date updateTime;
    private boolean isDeleted;
    private String appId;
    private String openId;
    private String newOpenId;
}
