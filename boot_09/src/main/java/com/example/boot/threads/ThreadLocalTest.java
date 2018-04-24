package com.example.boot.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/20
 */
public class ThreadLocalTest {

    // 创建一个 Integer 类型的线程本地变量
    static final ThreadLocal<Integer> local = ThreadLocal.withInitial(() -> 0);

    static class Task implements Runnable {

        private int num;

        Task(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            Integer i = local.get();
            while (++i < 10) ;
            System.out.println("Task " + num + " local num result is " + i);
        }
    }

    private static void test1() {
        System.out.println("main thread begin");
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 6; i++) {
            service.execute(new Task(i));
        }
        service.shutdown();
        System.out.println("main thread end");
    }

    public static void main(String[] args) {
        test1();
    }
}
