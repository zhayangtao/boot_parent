package com.example.boot_17;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/19
 */
public class CyclicBarrierTest {

    @Test
    public void test() throws InterruptedException {
        CyclicBarrier barrier0 = new CyclicBarrier(2);
        System.out.println("获取开启屏障的方数：" + barrier0.getParties());
        System.out.println("通过barrier.getNumberWaiting()获取正在等待的线程数：初始----" + barrier0.getNumberWaiting());
        System.out.println();
        new Thread(() -> {
            System.out.println("添加第一个等待线程---" + Thread.currentThread().getName());
            try {
                barrier0.await();
                System.out.println(Thread.currentThread().getName() + " is running");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " is terminated");
        }).start();
        Thread.sleep(10);
        System.out.println("通过barrier.getNumberWaiting()获取正在等待的线程数：添加第1个等待线程---" + barrier0.getNumberWaiting());
        Thread.sleep(10);
        System.out.println();
        new Thread(() -> {
            System.out.println("添加第二个等待线程---" + Thread.currentThread().getName());
            try {
                barrier0.await();
                System.out.println(Thread.currentThread().getName() + " is running");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " is terminated");
        }).start();
        System.out.println("通过barrier.getNumberWaiting()获取正在等待的线程数：添加第1个等待线程---" + barrier0.getNumberWaiting());
        Thread.sleep(10);
        System.out.println();
        new Thread(() -> {
            System.out.println("屏障打开之后，再有线程加入等待：" + Thread.currentThread().getName());
            try {
                barrier0.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " is terminated.");
        }).start();
        Thread.sleep(10);
        System.out.println("通过barrier.getNumberWaiting()获取正在等待的线程数：打开屏障之后---" + barrier0.getNumberWaiting());
    }

    @Test
    public void test1() throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(2);
        System.out.println("如果是一个初始的CyclicBarrier，则reset()之后，什么也不会发生");
        barrier.reset();
        System.out.println();
        Thread.sleep(200);
        //如果是一个已经打开一次的 CyclicBarrier，则reset()之后，什么也不会发生
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            executorService2.submit(() -> {
                try {
                    barrier.await();
                    System.out.println("屏障打开");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(100);
        System.out.println();
    }
}
