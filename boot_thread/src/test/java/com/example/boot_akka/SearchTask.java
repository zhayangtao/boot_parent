package com.example.boot_akka;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/03
 */
public class SearchTask implements Callable<Integer> {
    private int begin, end, searchValue;

    private static int[] arr;
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    private static final int Thread_Num = 2;
    private static AtomicInteger result = new AtomicInteger(-1);

    private SearchTask(int begin, int end, int searchValue) {
        this.begin = begin;
        this.end = end;
        this.searchValue = searchValue;
    }

    @Override
    public Integer call() throws Exception {
        return search(searchValue, begin, end);
    }

    private static int search(int searchValue, int begin, int end) {
        int i = 0;
        for (int j = begin; j < end; j++) {
            if (result.get() >= 0) {
                return result.get();
            }

            if (arr[i] == searchValue) {
                // 如果其他线程找到了结果
                if (!result.compareAndSet(-1, i)) {
                    return result.get();
                }
                return i;
            }
        }
        return -1;
    }

    private static int pSearch(int searchValue) throws ExecutionException, InterruptedException {
        int subArrSize = arr.length / Thread_Num + 1;
        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 0; i < arr.length; i += subArrSize) {
            int end = i + subArrSize;
            if (end >= arr.length) {
                end = arr.length;
            }
            futureList.add(executorService.submit(new SearchTask(searchValue, i, end)));
        }
        for (Future<Integer> integerFuture : futureList) {
            if (integerFuture.get() >= 0) {
                return integerFuture.get();
            }
        }
        return -1;
    }
}
