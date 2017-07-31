package com.sun.members.dao;

import com.sun.members.entity.Role;

import java.util.List;

public interface RoleDao {
    int delete(Integer id);

    int insert(Role record);

    Role selectById(Integer id);

    List<Role> selectAll();

    int update(Role record);
}