package com.sun.controller;

import com.sun.entity.Student;
import com.sun.service.StudentService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengjikun on 2017/2/15.
 */
@Controller
@Log4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/get")
    @ResponseBody
    Student get() throws Exception {
        return studentService.getStu(1);
    }

    @RequestMapping("/save")
    @ResponseBody
    String save() throws Exception {
        Student student = Student.builder().name("test1").birth("2011-11-11").address("beijing").sex(1).build();
        studentService.save(student);
        return "添加成功";
    }

    @RequestMapping("/batchInsert")
    @ResponseBody
    String batchInsert() throws Exception {
        List<Student> studentList = new ArrayList<>();
        for(int i=0; i<5; i++){
            Student student = Student.builder().name("test1").birth("2011-11-11").address("beijing").sex(1).build();
            studentList.add(student);
        }
        studentService.batchInsert(studentList);
        return "批量添加成功";
    }

}