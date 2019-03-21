package com.example.boot_17;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/18
 */
public class AtomicTest {

    @Test
    public void test() {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1, 2, 3, 4, 5});
        AtomicReferenceArray<String> atomicReferenceArray = new AtomicReferenceArray<>(new String[]{"1"});
    }

    static class MyVolatileType {
        private volatile int index;
        private static final AtomicIntegerFieldUpdater inUpdater = AtomicIntegerFieldUpdater.newUpdater(MyVolatileType.class, "index");
    }


    static class MyNum {
        // 共享变量
        int num;
        ThreadLocal<Integer> threadLocalNum = new ThreadLocal<>();

        public MyNum(int num, Integer threadLocalNum) {
            this.num = num;
            this.threadLocalNum.set(threadLocalNum);
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public ThreadLocal<Integer> getThreadLocalNum() {
            return threadLocalNum;
        }

        public void setThreadLocalNum(ThreadLocal<Integer> threadLocalNum) {
            this.threadLocalNum = threadLocalNum;
        }
    }

    @Test
    public void test1() throws InterruptedException {
        MyNum myNum = new MyNum(0, 0);
        System.out.println("线程[" + Thread.currentThread().getName()
                + "]----num：" + myNum.getNum() + ",threadLocalNum：" + myNum.getThreadLocalNum().get() + "\n");
        //多线程运行
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                myNum.setNum(myNum.getNum() + 1);
                //打印结果
                System.out.println("线程[" + Thread.currentThread().getName()
                        + "]----num: " + myNum.getNum());
                if (myNum.getThreadLocalNum().get() != null) {
                    myNum.getThreadLocalNum().set(myNum.getThreadLocalNum().get() + 1);
                    //打印结果
                    System.out.println("线程[" + Thread.currentThread().getName()
                            + "]----threadLocalNum: " + myNum.getThreadLocalNum().get());
                } else {
                    System.out.println("线程[" + Thread.currentThread().getName()
                            + "]----threadLocalNum is null ,threadLocalNum to " + 1);
                    myNum.getThreadLocalNum().set(1);
                }
            }).start();

            Thread.sleep(100);
            System.out.println();
        }
        Thread.sleep(100);
        System.out.println("线程[" + Thread.currentThread().getName()
                + "]----num：" + myNum.getNum() + ",threadLocalNum：" + myNum.getThreadLocalNum().get());

    }

    @Test
    public void test2() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "(await0) is awaiting....");
            try {
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName() + "(await0) is terminated because the latch's count is zero.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
