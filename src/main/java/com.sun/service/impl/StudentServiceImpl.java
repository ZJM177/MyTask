package com.sun.service.impl;

import com.sun.entity.Student;
import com.sun.dao.StudentMapper;
import com.sun.service.StudentService;
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
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student getStu(int id){
        return studentMapper.selectById(id);
    }

    @Override
    public void save(Student student){
        studentMapper.insertOne(student);
    }

    //批量插入，测试事务
    @Transactional
    @Override
    public void batchInsert(List<Student> studentList){
        Consumer<Student> consumer = student -> {
            save(student);
        };
        studentList.stream().forEach(consumer);
        //模拟异常，事务回滚
        int i = 1/0;
    }


}
