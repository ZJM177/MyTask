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
public class RoleResource extends BasicEntity implements Serializable {
    private Integer id;

    private Integer roleId;

    private Integer resourceId;

    private static final long serialVersionUID = 1L;
}