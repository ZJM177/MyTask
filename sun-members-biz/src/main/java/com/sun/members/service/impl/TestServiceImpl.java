package com.sun.members.service.impl;

import com.sun.members.dao.TestDao;
import com.sun.members.service.TestService;
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
