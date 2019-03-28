package com.example.boot_akka;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/25
 */
public class ReenterLock implements Runnable {
    private static ReentrantLock lock = new ReentrantLock();
    private static int i = 0;
    private static int k = 0;

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        for (int j = 0; j < 10000000; j++) {
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        start = System.currentTimeMillis();
        lock.lock();
        try {
            for (int j = 0; j < 10000000; j++) {
                k++;
            }
        } finally {
            lock.unlock();
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock reenterLock = new ReenterLock();
        Thread thread = new Thread(reenterLock);
        Thread thread1 = new Thread(reenterLock);
        thread.start();
//        thread1.start();
        thread.join();
//        thread1.join();
        System.out.println(i);
        System.out.println(k);
    }
}
