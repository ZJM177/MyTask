package com.sun.dao;

import com.sun.entity.Student;

/**
 * Created by pengjikun on 2017/2/15.
 */
public interface StudentMapper{

    Student selectById(int id);

    void insertOne(Student student);

    void delete();

    void update();
}
