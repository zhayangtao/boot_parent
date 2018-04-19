package com.example.boot.test;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/09
 */
public class Handle implements Runnable {

    private String name;

    private Handle(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " start. Time = " + new Date());
        process();
        System.out.println(name + " end. Time = " + new Date());
    }

    private void process() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * 测试缓冲线程池
     */
    private static void testCachedThreadPool() {
        System.out.println("Main: starting at: " + new Date());
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.execute(new Handle(String.valueOf(i)));
        }
        service.shutdown(); // 并不会马上关闭线程池，但之后不能再往线程池中加线程
        System.out.println("Main: Finished all threads at: " + new Date());
    }

    private static void testFixedThreadPool() {
        System.out.println("fixed Main: start at: " + new Date());
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            service.execute(new Handle(String.valueOf(i)));
        }
        service.shutdown();
        System.out.println("fixed main: finished at: " + new Date());
    }

    private static void testSingleThreadPool() {
        System.out.println("Single main thread: starting at: " + new Date());
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            service.execute(new Handle(String.valueOf(i)));
        }
        service.shutdown();
        System.out.println("Single main thread: finished at: " + new Date());
    }

    private static void testScheduledThreadPool() {
        System.out.println("scheduled main thread: starting at: " + new Date());
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.schedule(new Handle(String.valueOf(i)), 10, TimeUnit.SECONDS);
        }
        service.shutdown();
        while (!service.isTerminated()) {
            // 等待任务结束
        }
        System.out.println("scheduled main thread: finished at: " + new Date());
    }

    public static void main(String[] args) {
//        testCachedThreadPool();
//        testFixedThreadPool();
//        testSingleThreadPool();
        testScheduledThreadPool();
    }
}
