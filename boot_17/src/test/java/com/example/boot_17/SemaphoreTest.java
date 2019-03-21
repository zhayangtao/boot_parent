package com.example.boot_17;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/15
 * 场景说明：
 *
 * 模拟学校食堂的窗口打饭过程
 * 学校食堂有2个打饭窗口
 * 学校中午有20个学生 按次序 排队打饭
 * 每个人打饭时耗费时间不一样
 * 有的学生耐心很好，他们会一直等待直到打到饭
 * 有的学生耐心不好，他们等待时间超过了心里预期，就不再排队，而是回宿舍吃泡面了
 * 有的学生耐心很好，但是突然接到通知，说是全班聚餐，所以也不用再排队，而是去吃大餐了
 * 重点分析
 *
 * 食堂有2个打饭窗口：需要定义一个permits=2的Semaphore对象。
 * 学生 按次序 排队打饭：此Semaphore对象是公平的。
 * 有20个学生：定义20个学生线程。
 * 打到饭的学生：调用了acquireUninterruptibly()方法，无法被中断
 * 吃泡面的学生：调用了tryAcquire(timeout,TimeUnit)方法，并且等待时间超时了
 * 吃大餐的学生：调用了acquire()方法，并且被中断了
 */
public class SemaphoreTest {
    private static Semaphore semaphore = new Semaphore(2, true);//公平队列-FIFO

    static class Student implements Runnable {
        /**
         * 打饭方式
         * 0    一直等待直到打到饭
         * 1    等了一会不耐烦了，回宿舍吃泡面了
         * 2    打饭中途被其他同学叫走了，不再等待
         */
        private int type;
        private String name;
        private Semaphore semaphore;

        Student(String name, Semaphore semaphore, int type) {
            this.name = name;
            this.semaphore = semaphore;
            this.type = type;
        }

        @Override
        public void run() {
            switch (type) {
                case 0:
                    // 排队
                    semaphore.acquireUninterruptibly();
                    try {
                        TimeUnit.MILLISECONDS.sleep(RandomUtils.nextLong(1000, 3000));
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                    }
                    // 将打饭机会让后后面的同学
                    semaphore.release();
                    System.out.println(name + "达到了饭");
                    break;
                //这个学生没有耐心，等了1000毫秒没打到饭，就回宿舍泡面了
                case 1:
                    try {
                        if (semaphore.tryAcquire(RandomUtils.nextInt(6000, 16000))) {
                            try {
                                TimeUnit.MILLISECONDS.sleep(RandomUtils.nextLong(1000, 3000));
                            } catch (InterruptedException e) {
//                                e.printStackTrace();
                            }
                            semaphore.release();
                            System.out.println(name + "达到了饭");
                        } else {
                            //回宿舍吃泡面
                            System.out.println(name + " 回宿舍吃泡面.");
                        }
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
                    break;
                //这个学生也很有耐心，但是他们班突然宣布聚餐，它只能放弃打饭了
                case 2:
                    try {
                        semaphore.acquire();
                        // 打饭
                        try {
                            TimeUnit.MILLISECONDS.sleep(RandomUtils.nextLong(1000, 3000));
                        } catch (InterruptedException e) {
//                            e.printStackTrace();
                        }
                        //将打饭机会让后后面的同学
                        semaphore.release();
                        System.out.println(name + "达到了饭");
                    } catch (InterruptedException e) {
                        System.out.println(name + " 全部聚餐，不再打饭.");
//                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*Thread[] students101 = new Thread[5];
        for (int i = 0; i < 20; i++) {
            if (i < 10) {
                new Thread(new Student("打饭学生" + i, SemaphoreTest.semaphore, 0)).start();
            } else if (i < 15) {//这5个学生没有耐心打饭，只会等1000毫秒
                new Thread(new Student("泡面学生" + i, SemaphoreTest.semaphore, 1)).start();
            } else {//这5个学生没有耐心打饭
                students101[i - 15] = new Thread(new Student("聚餐学生" + i, SemaphoreTest.semaphore, 2));
                students101[i - 15].start();
            }
        }
        TimeUnit.SECONDS.sleep(5);
        for (int i = 0; i < 5; i++) {
            students101[i].interrupt();
        }*/
//        System.out.println(Thread.currentThread().getState());

        SynchronizedIncrement increment = new SynchronizedIncrement();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            new Thread(increment::increment).start();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        TimeUnit.SECONDS.sleep(5);

        LockIncrement lockIncrement = new LockIncrement();
        start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            new Thread(lockIncrement::increment).start();
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static class SynchronizedIncrement {
        private int count = 1;

        synchronized void increment() {
            count++;
        }
    }

    public static class LockIncrement {
        private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
        private int count = 1;

        public void increment() {
            readWriteLock.writeLock().lock();
            count++;
            readWriteLock.writeLock().unlock();
        }
    }

}
