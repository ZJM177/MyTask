package com.sun.task.service.impl;

import com.sun.task.dao.TestDao;
import com.sun.task.service.TestService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pengjikun on 2017/2/15.
 */
@Service
@Log4j
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;

    @Override
    public Object get(int id) {
        return testDao.selectById(id);

    }
}
