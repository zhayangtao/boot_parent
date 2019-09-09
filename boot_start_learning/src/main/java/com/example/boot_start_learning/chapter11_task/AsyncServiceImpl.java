package com.example.boot_start_learning.chapter11_task;

import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/30
 */
@Service
public class AsyncServiceImpl implements AsyncService {
    @Override
    public void asyncTask() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Complete execution of async.task");
    }

    @Override
    public Future<String> asyncWithReturn(String name) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Complete execution of async.task with return for " + name);
        return new AsyncResult<>("Hello " + name);
    }
}
