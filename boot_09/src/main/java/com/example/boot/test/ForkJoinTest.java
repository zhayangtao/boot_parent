package com.example.boot.test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/08
 */
public class ForkJoinTest extends RecursiveTask<Long> {

    private static final int DEFAULT_CAPACITY = 10000; // 最大计算容量

    private int start;

    private int end;

    public ForkJoinTest(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        // 分为两种情况进行计算
        long sum = 0;
        //1 任务量在最大容量内
        if (end - start < DEFAULT_CAPACITY) {
            for (int i = start; i < end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            // 递归
            ForkJoinTest test1 = new ForkJoinTest(start, middle);
            ForkJoinTest test2 = new ForkJoinTest(middle + 1, end);
            // 执行任务
            test1.fork();
            test2.fork();
            // 等待返回结果
            sum = test1.join() + test2.join();
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTest forkJoinTest = new ForkJoinTest(1, 10000000);
        long startTime = System.currentTimeMillis();
        // 调用 invoke 直接等待返回结果
        long result = forkJoinPool.invoke(forkJoinTest);
        System.out.println("fock/join计算结果耗时"+(System.currentTimeMillis() - startTime));

        long sum = 0;
        long normalStartTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            sum += i;
        }
        System.out.println("普通计算结果耗时"+(System.currentTimeMillis() - normalStartTime));
    }
}
