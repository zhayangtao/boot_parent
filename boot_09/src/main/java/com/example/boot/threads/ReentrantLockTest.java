package com.example.boot.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/19
 */
public class ReentrantLockTest {

    static class NumberWrapper {
        int value = 1;
    }

    public static void main(String[] args) {
        // 初始化可重入锁
        final Lock lock = new ReentrantLock();

        // 第一个条件当屏幕输出到3
        final Condition reachThreeCondition = lock.newCondition();
        // 第二个条件当屏幕输出到6
        final Condition reachSixCondition = lock.newCondition();

        final NumberWrapper num = new NumberWrapper();
        // 初始化线程A
        Thread threadA = new Thread(() -> {
            try {
                // 先获得锁
                lock.lock();
                System.out.println("threadA start write");
                while (num.value <= 3) {
                    System.out.println(num.value);
                    num.value++;
                }
                // 输出到3时，调用 signal，告诉 B 线程开始执行
                reachThreeCondition.signal();
            } finally {
                lock.unlock();
            }

            lock.lock();
            try {
                reachSixCondition.await();
                System.out.println("threadA start write");
                // 输出剩余数字
                while (num.value <= 9) {
                    System.out.println(num.value);
                    num.value++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "threadA");

        Thread threadB = new Thread(() -> {
           try {
               lock.lock();
               while (num.value <= 3) {
                   // 等待3输出完毕的信号
                   reachThreeCondition.await();
               }
           } catch (InterruptedException e) {
               e.printStackTrace();
           } finally {
               lock.unlock();
           }

           try {
               lock.lock();
               System.out.println("threadB start write");
               // 已经收到信号，开始输出4,5,6
               while (num.value <= 6) {
                   System.out.println(num.value);
                   num.value++;
               }
               reachSixCondition.signal();
           } finally {
               lock.unlock();
           }
        }, "threadB");

        threadA.start();
        threadB.start();
    }
}
