package com.example.boot_15.threads;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/11
 */
public class ForkJoinTest extends RecursiveTask<Long> {
    private Long start;
    private Long end;
    private static final Long critical = 100000L;

    private ForkJoinTest(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        // 判断是否拆分完毕
        Long length = end - start;
        if (length <= critical) {
            Long sum = 0L;
            for (Long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // 没有拆分完就开始拆分
            Long middle = (end + start) / 2; // 计算两个值的中间值
            ForkJoinTest right = new ForkJoinTest(start, middle);
            right.fork();
            ForkJoinTest left = new ForkJoinTest(middle + 1, end);
            left.fork(); // 拆分，压入线程队列
            return right.join() + left.join();
        }
    }

    private static void test() {
        // ForkJoin 实现
        long LL = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool(); // 实现 ForkJoin
        ForkJoinTask<Long> task = new ForkJoinTest(0L, 100000000L); // 参数为起始值与结束值
        Long invoke = forkJoinPool.invoke(task);
        long LLL = System.currentTimeMillis();
        System.out.println("invoke = " + invoke + "  time: " + (LLL - LL));
    }

    private static void test2() {
        // 普通线程实现
        Long x = 0L;
    }

    private static void test3() {
        // Java 8 并行流
        long ll = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(0, 100000000).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("reduce = " + reduce + "  time: " + (end - ll));
    }

    public static void main(String[] args) {
        ForkJoinTest.test();
        ForkJoinTest.test3();
    }

}
