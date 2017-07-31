package com.sun.members.dao;

import com.sun.members.entity.Resource;

import java.util.List;

public interface ResourceDao {
    int delete(Integer id);

    int insert(Resource record);

    Resource selectById(Integer id);

    List<Resource> selectAll();

    int update(Resource record);
}