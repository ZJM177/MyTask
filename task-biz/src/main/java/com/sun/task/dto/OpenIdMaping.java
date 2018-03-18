package com.sun.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import java.io.Serializable;

/**
 * Created by pengjikun on 2018/3/16.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenIdMaping implements Serializable{
    private int id;
    private String oldOpenId;
    private String newOpenId;
    private String mobile;
    private String memberCode;
    private int No;
    private int status;
}
