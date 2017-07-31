package com.sun.members.dao;

import com.sun.members.entity.User;

import java.util.List;

public interface UserDao {
    int delete(Integer id);

    int insert(User record);

    User selectById(Integer id);

    List<User> selectAll();

    int update(User record);
}