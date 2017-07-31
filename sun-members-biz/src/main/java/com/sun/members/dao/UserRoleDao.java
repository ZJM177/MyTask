package com.sun.members.dao;

import com.sun.members.entity.UserRole;

import java.util.List;

public interface UserRoleDao {
    int delete(Integer id);

    int insert(UserRole record);

    UserRole selectById(Integer id);

    List<UserRole> selectAll();

    int update(UserRole record);
}