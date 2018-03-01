package com.example.boot_14_async.controller;

import com.example.boot_14_async.service.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/2/2
 */
@RestController
@RequestMapping("/async")
public class AsyncTaskController {

    @Autowired
    private AsyncTask asyncTask;

    @RequestMapping("")
    public String doTask() throws InterruptedException {
        long start = System.currentTimeMillis();
        asyncTask.task1();
        asyncTask.task2();
        asyncTask.task3();
        Future<String> task4 = asyncTask.task4();
        Future<String> task5 = asyncTask.task5();
        Future<String> task6 = asyncTask.task6();
        for (;;) {
            if (task4.isDone() && task5.isDone() && task6.isDone()) {
                break;
            }
        }
        long end = System.currentTimeMillis();
        return "task任务总耗时:" + (end - start) + "ms";
    }


}
