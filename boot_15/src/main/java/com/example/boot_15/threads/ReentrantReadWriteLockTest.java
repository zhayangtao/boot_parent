package com.example.boot_15.threads;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/02/18
 */
public class ReentrantReadWriteLockTest {
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private void get(Thread thread) {
        readWriteLock.readLock().lock();
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName() + "正在进行读操作");
            }
            System.out.println(thread.getName() + "读操作完毕");
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        final ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();

        new Thread(() -> test.get(Thread.currentThread())).start();
        new Thread(() -> test.get(Thread.currentThread())).start();
    }
}
