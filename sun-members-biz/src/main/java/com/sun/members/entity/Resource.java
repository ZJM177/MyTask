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
public class Resource extends BasicEntity implements Serializable {
    private Integer id;

    /** 该资源对应的菜单url */
    private String url;

    /** url对应的页面上的按钮权限，用json表达 */
    private String buttonPermissions;

    private static final long serialVersionUID = 1L;
}