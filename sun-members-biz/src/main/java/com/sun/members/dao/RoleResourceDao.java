package com.sun.members.dao;

import com.sun.members.entity.RoleResource;

import java.util.List;

public interface RoleResourceDao {
    int delete(Integer id);

    int insert(RoleResource record);

    RoleResource selectById(Integer id);

    List<RoleResource> selectAll();

    int update(RoleResource record);
}