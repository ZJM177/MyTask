package com.sun.service;

import com.sun.entity.Student;
import com.sun.dao.StudentMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by pengjikun on 2017/2/15.
 */
public interface StudentService {

    //整合mybatis 方式1（新微信方式）
    Student getStu(int id);

    //插入
    void save(Student student);

    //批量插入，测试事务
    void batchInsert(List<Student> studentList);


}
