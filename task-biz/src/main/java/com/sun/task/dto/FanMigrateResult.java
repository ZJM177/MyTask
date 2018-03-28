package com.sun.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

/**
 * Created by pengjikun on 2018/3/27.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FanMigrateResult {
    private String ori_openid;
    private String new_openid;
    private String err_msg;
}
