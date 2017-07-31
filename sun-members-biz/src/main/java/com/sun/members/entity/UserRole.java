package com.sun.members.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole extends BasicEntity implements Serializable {
    private Integer id;

    /** 用户表id */
    private Integer userId;

    /** 角色表id */
    private Integer roleId;

    private static final long serialVersionUID = 1L;
}