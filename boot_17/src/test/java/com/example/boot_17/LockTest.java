package com.example.boot_17;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/17
 */
public class LockTest {
    private static ReentrantLock lock = new ReentrantLock(true);
    private static Condition condition = lock.newCondition();
    private static boolean stop = false;

    public static void test() {
        new Thread(() -> {
            System.out.println("lock A is running");
            lock.lock();
            try {
                condition.await();
                while (!stop);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Lock A is terminated.");
        }).start();
        new Thread(() -> {
            System.out.println("lock B is running");
            lock.lock();
            stop = true;
            condition.signal();
            lock.unlock();
            System.out.println("Lock B is terminated.");
        }).start();
    }


    public static void main(String[] args) {
        test();
    }
}
