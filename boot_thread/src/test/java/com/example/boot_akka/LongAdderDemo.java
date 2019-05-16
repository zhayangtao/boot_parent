package com.example.boot_akka;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/09
 */
public class LongAdderDemo {
    private static final int MAX_THREADS = 3; // 线程数
    private static final int TASK_COUNT = 3; // 任务数
    private static final int TARGET_COUNT = 10_000_000; // 目标总数

    private AtomicLong acount = new AtomicLong(0L); // 无锁原子操作
    private LongAdder lacount = new LongAdder();
    private long count = 0;

    static CountDownLatch cdlsync = new CountDownLatch(TASK_COUNT);
    static CountDownLatch cdlatomic = new CountDownLatch(TASK_COUNT);
    static CountDownLatch cdladdr = new CountDownLatch(TASK_COUNT);

    private synchronized long inc() { // 有锁加法
        return ++count;
    }

    private synchronized long getCount() { // 有锁操作
        return count;
    }

    private class SyncThread implements Runnable {
        private String name;
        private long startTime;
        LongAdderDemo out;

        SyncThread(long startTime, LongAdderDemo out) {
            this.startTime = startTime;
            this.out = out;
        }

        @Override
        public void run() {
            long v = out.getCount();
            while (v < TARGET_COUNT) {
                v = out.inc();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("SyncThread spend:" + (endTime - startTime) + "ms" + " v=" + v);
            cdlsync.countDown();
        }
    }

    public void testSync() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        SyncThread syncThread = new SyncThread(startTime, this);
        for (int i = 0; i < TASK_COUNT; i++) {
            executorService.submit(syncThread);
        }
        cdlsync.await();
        executorService.shutdown();
    }

    private class AtomicThread implements Runnable {
        private String name;
        private long startTime;

        AtomicThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = acount.get();
            while (v < TARGET_COUNT) {
                v = acount.incrementAndGet(); // 无锁加法
            }
            long endTime = System.currentTimeMillis();
            System.out.println("AtomicThread spend:" + (endTime - startTime) + "ms" + " v=" + v);
            cdlatomic.countDown();
        }
    }

    public void testAtomic() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        AtomicThread atomicThread = new AtomicThread(startTime);
        for (int i = 0; i < TASK_COUNT; i++) {
            executorService.submit(atomicThread);
        }
        cdlatomic.await();
        executorService.shutdown();
    }

    private class LongAdderThread implements Runnable {
        private String name;
        private long startTime;

        LongAdderThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = acount.get();
            while (v < TARGET_COUNT) {
                lacount.increment();
                v = lacount.sum();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("LongAdderThread spend:" + (endTime - startTime) + "ms" + " v=" + v);
            cdladdr.countDown();
        }
    }

    public void testLongAddr() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        LongAdderThread longAdderThread = new LongAdderThread(startTime);
        for (int i = 0; i < TASK_COUNT; i++) {
            executorService.submit(longAdderThread);
        }
        cdladdr.await();
        executorService.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        LongAdderDemo demo = new LongAdderDemo();
        demo.testSync();
        demo.testAtomic();
        demo.testLongAddr();
    }
}
