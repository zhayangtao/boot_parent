package com.example.boot.threads;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/20
 * 线程组测试
 */
public class ThreadGroupTest {

    static class Result {
        private String name;

        public void setName(String name) {
            this.name = name;
        }
    }

    static class SearchTask implements Runnable {

        private Result result;

        SearchTask(Result result) {
            this.result = result;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println("Thread start " + name);
            try {
                doTask();
                result.setName(name);
            } catch (InterruptedException e) {
                System.out.printf("Thread %s: interrupted\n", name);
            }
            System.out.println("Thread end " + name);
        }

        private void doTask() throws InterruptedException {
            Random random = new Random(new Date().getTime());
            int value = (int) (random.nextDouble() * 100);
            System.out.printf("Thread %s: %d\n", Thread.currentThread().getName(), value);
            TimeUnit.SECONDS.sleep(value);
        }
    }

    public static void main(String[] args) {
        System.out.println("main thread start");
        // 创建5个线程，在 group 中进行管理
        ThreadGroup threadGroup = new ThreadGroup("Searcher");
        Result result = new Result();
        SearchTask searchTask = new SearchTask(result);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(threadGroup, searchTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Number of Threads: %d\n", threadGroup.activeCount());
        System.out.println("Information about the Thread Group\n");
        threadGroup.list();

        // 复制 group 中的 thread 信息
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (int i = 0; i < threadGroup.activeCount(); i++) {
            System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
        }
        waitFinish(threadGroup);
        threadGroup.interrupt();
        System.out.println("main thread end");

    }

    private static void waitFinish(ThreadGroup threadGroup) {
        while (threadGroup.activeCount() > 0) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

