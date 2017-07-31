package com.sun.members.service.impl;

import com.sun.members.service.LoginService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pengjikun on 2017/2/15.
 */
@Service
@Log4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student get(int id) {
        return studentMapper.selectByPrimaryKey(id);
    }
}
