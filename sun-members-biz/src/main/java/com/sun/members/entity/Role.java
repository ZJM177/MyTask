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
public class Role extends BasicEntity implements Serializable {
    private Integer id;

    /** 角色名 */
    private String roleName;

    /** 公司id，表示这个角色属于哪个公司 */
    private Integer companyId;

    private static final long serialVersionUID = 1L;
}