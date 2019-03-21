package com.example.boot_17;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/18
 */
public class LockDemo {
    private static Lock lock = new ReentrantLock(false);

    @Test
    public void test() throws InterruptedException {
        // 线程0
        new Thread(() -> {
            System.out.println("线程[" + Thread.currentThread().getName() + "]");
            lock.lock();
            System.out.println("线程[" + Thread.currentThread().getName() + "]");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("线程[" + Thread.currentThread().getName() + "]");
            }
        }).start();
        TimeUnit.MILLISECONDS.sleep(10);
        // 线程1通过 lock.lock() 持续尝试获取锁
        new Thread(() -> {
            System.out.println("线程[" + Thread.currentThread().getName() + "]");
            lock.lock();
            System.out.println("线程[" + Thread.currentThread().getName() + "]");
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("线程[" + Thread.currentThread().getName() + "]");
            }
        }).start();
        //线程2通过lock.tryLock()尝试去获取一次锁
        new Thread(() -> {
            System.out.println("线程[" + Thread.currentThread().getName() + "]");
            if (lock.tryLock()) {
                System.out.println("线程[" + Thread.currentThread().getName() + "]");
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println("线程[" + Thread.currentThread().getName() + "]");
                }
            }
        }).start();
        // 线程3 lock.tryLock(long, TImeUnit)
        new Thread(() -> {
            System.out.println("线程[" + Thread.currentThread().getName() + "]");
            try {
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    System.out.println("线程[" + Thread.currentThread().getName() + "]");
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (InterruptedException e) {
//                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("线程[" + Thread.currentThread().getName() + "]");
            }
        }).start();
        // 线程4 lock.lockInterruptibly()
        Thread thread4 = new Thread(() -> {
            System.out.println("线程[" + Thread.currentThread().getName() + "]");
            try {
                lock.lockInterruptibly();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
//                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("线程[" + Thread.currentThread().getName() + "]");
            }
        });
        thread4.start();
        TimeUnit.SECONDS.sleep(3);
        thread4.interrupt();
    }
}
