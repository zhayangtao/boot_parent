package com.example.boot_13_redis.jpa;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/07
 */
@Component
public class Task {

    private static Random random = new Random();

    @Async
    public void taskOne() {
        System.out.println("start taskone");
        long start = System.currentTimeMillis();
        TimeUnit.SECONDS.toSeconds(random.nextInt(10));
        long end = System.currentTimeMillis();
        System.out.println("任务一执行时间："+(end- start));
    }

    @Async
    public void taskTwo() throws Exception {
        System.out.println("开始执行任务二");
        long start=System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end=System.currentTimeMillis();
        System.out.println("任务二执行时间："+(end- start));
    }
    @Async
    public void taskThree() throws Exception {
        System.out.println("开始执行任务三");
        long start=System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end=System.currentTimeMillis();
        System.out.println("任务三执行时间："+(end- start));
    }
}
