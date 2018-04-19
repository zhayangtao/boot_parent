package com.example.boot.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/16
 */
public class SumTask extends RecursiveTask<List<String>> {

    private static final int MAX = 10;

    private List<String> list;

    private int start;

    private int end;

    private SumTask(List<String> list, int start, int end) {
        this.list = list;
        this.start = start;
        this.end = end;
    }

    @Override
    protected List<String> compute() {
        if ((end - start) < MAX) {
            List<String> result = new ArrayList<>();
            for (int i = start; i < end; i++) {
                result.add(list.get(i));
            }
            return result;
        } else {
            List<String> result = new ArrayList<>();
            System.out.println("任务分解");
            int middle = (end + start) / 2;

            SumTask left = new SumTask(list, start, middle);
            SumTask right = new SumTask(list, middle, end);

            left.fork();
            right.fork();

            result.addAll(left.join());
            result.addAll(right.join());

            return result;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("2");
        list.add("2");
        list.add("2");
        list.add("2");
        list.add("2");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("4");

        System.out.println(list);
        System.out.println();

        System.out.println("use thread");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<List<String>> future = forkJoinPool.submit(new SumTask(list, 0, list.size()));
        System.out.println(future.get());
        forkJoinPool.shutdown();
    }
}
