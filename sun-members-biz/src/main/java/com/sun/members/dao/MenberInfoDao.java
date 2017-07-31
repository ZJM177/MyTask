package com.sun.members.dao;

import com.sun.members.entity.MenberInfo;

import java.util.List;

public interface MenberInfoDao {
    int delete(Integer id);

    int insert(MenberInfo record);

    MenberInfo selectById(Integer id);

    List<MenberInfo> selectAll();

    int update(MenberInfo record);
}