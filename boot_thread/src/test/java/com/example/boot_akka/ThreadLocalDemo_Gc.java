package com.example.boot_akka;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/28
 */
public class ThreadLocalDemo_Gc {
    private static volatile ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected void finalize() throws Throwable {
            System.out.println(this.toString() + " is gc");
        }
    };
    private static volatile CountDownLatch countDownLatch = new CountDownLatch(10000);

    private static class ParseDate implements Runnable {
        int i = 0;

        ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (threadLocal.get() == null) {
                    threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") {
                        @Override
                        protected void finalize() throws Throwable {
                            System.out.println(this.toString() + " is gc");
                        }
                    });
                    System.out.println(Thread.currentThread().getId() + ":create SimpleDateFormat");
                }
                Date date = threadLocal.get().parse("2019-03-28 19:29:" + i % 60);
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10000; i++) {
            executorService.execute(new ParseDate(i));
        }
        countDownLatch.await();
        System.out.println("mission complete!");
        threadLocal = null;
        System.gc();
        System.out.println("first GC complete!");
        // 在设置 ThreadLocal 的时候，会清除 ThreadLocalMap 中的无效对象
        threadLocal = new ThreadLocal<>();
        countDownLatch = new CountDownLatch(10000);
        for (int i = 0; i < 10000; i++) {
            executorService.execute(new ParseDate(i));
        }
        countDownLatch.await();
        Thread.sleep(1000);
        System.gc();
        System.out.println("second GC complete");
    }
}
