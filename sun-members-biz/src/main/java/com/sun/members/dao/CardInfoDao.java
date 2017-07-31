package com.sun.members.dao;

import com.sun.members.entity.CardInfo;

import java.util.List;

public interface CardInfoDao {
    int delete(Integer id);

    int insert(CardInfo record);

    CardInfo selectById(Integer id);

    List<CardInfo> selectAll();

    int update(CardInfo record);
}