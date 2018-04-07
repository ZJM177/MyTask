package com.sun.task.service;

/**
 * Created by pengjikun on 2017/2/15.
 */
public interface TestService {

    Object get(int id);

    /**
     * 测试多线程异步执行
     * @param i
     * @return
     */
    void testAsync(int i);

}
