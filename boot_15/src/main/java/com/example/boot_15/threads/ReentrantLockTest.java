package com.example.boot_15.threads;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/02/18
 */
public class ReentrantLockTest {
    private ArrayList<Integer> arrayList = new ArrayList<>();
    private Lock lock = new ReentrantLock();

    private void insert(Thread thread) {
        lock.lock();

        try {
            System.out.println(thread.getName() + "得到了锁");
            for (int i = 0; i < 5; i++) {
                arrayList.add(i);
            }
        } finally {
            System.out.println(thread.getName() + "释放了锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReentrantLockTest test = new ReentrantLockTest();
        new Thread(() -> test.insert(Thread.currentThread())).start();
        new Thread(() -> test.insert(Thread.currentThread())).start();
    }
}
