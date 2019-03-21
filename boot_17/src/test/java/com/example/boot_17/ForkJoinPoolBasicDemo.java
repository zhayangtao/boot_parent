package com.example.boot_17;

import java.util.concurrent.ForkJoinPool;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/21
 * ForkJoin-ForkJoinPool的方法学习
 */
public class ForkJoinPoolBasicDemo {


    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();//无参则使用 cpu 核心数
        ForkJoinPool forkJoinPool1 = new ForkJoinPool(4);
        Runnable runnable = null;
        forkJoinPool.submit(runnable);
        Integer integer = null;
        forkJoinPool.submit(runnable, integer);

        forkJoinPool.shutdown();
        forkJoinPool1.shutdown();
    }


    public void test() {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long start = System.currentTimeMillis();
        forkJoinPool.submit(runnable);
        long end = System.currentTimeMillis();

    }
}
