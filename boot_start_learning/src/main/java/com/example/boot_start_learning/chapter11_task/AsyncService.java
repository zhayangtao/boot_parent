package com.example.boot_start_learning.chapter11_task;

import java.util.concurrent.Future;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/30
 */
public interface AsyncService {

    void asyncTask();

    Future<String> asyncWithReturn(String name);

}
