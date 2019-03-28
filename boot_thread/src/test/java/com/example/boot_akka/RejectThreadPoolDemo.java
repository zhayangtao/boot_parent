package com.example.boot_akka;

import java.util.concurrent.*;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/27
 */
public class RejectThreadPoolDemo {
    private static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()
                    + ":Thread Id:" + Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        ExecutorService executorService = new ThreadPoolExecutor(
                5,
                5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10),
                (r, executor) -> System.out.println(r + " is discard"));
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.submit(task);
            Thread.sleep(10);
        }


    }


    public void test() {
        // 自定义 ThreadFactory
        ExecutorService executorService = new ThreadPoolExecutor(
                5,
                5,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(),
                r -> {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    System.out.println("create " + t);
                    return t;
                });
    }

}
