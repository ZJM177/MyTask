package com.sun.task;

import com.sun.task.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by pengjikun on 2018/3/16.
 */
public class MyTest extends BasicTest{

    @Autowired
    private TestService testService;

    @Test
    public void test(){
        Object o = testService.get(1);
        System.out.println(o);
    }

}
