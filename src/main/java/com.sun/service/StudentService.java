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
@Service
@Log4j
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    //整合mybatis 方式1（新微信方式）
    public Student getStu1(int id){
        return studentMapper.selectById(id);
    }
    //插入
    public void insert1(Student student){
        studentMapper.insertOne(student);
    }

    //批量插入，测试事务
    @Transactional
    public void batchInsert(List<Student> studentList){
        Consumer<Student> consumer = student -> {
            insert1(student);
        };
        studentList.stream().forEach(consumer);
        //模拟异常，事务回滚
        int i = 1/0;
    }


}
