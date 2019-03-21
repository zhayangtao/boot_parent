package com.example.boot_17;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/21
 */
public class RecursiveTaskTest {
    static class Sum extends RecursiveTask<Long> {
        private final int[] array;
        private final int lo, hi;
        private static final int threshold = 600;

        Sum(int[] array, int lo, int hi) {
            this.array = array;
            this.lo = lo;
            this.hi = hi;
        }

        static long sum(int[] array) {
            return ForkJoinPool.commonPool().invoke(new Sum(array, 0, array.length));
        }

        @Override
        protected Long compute() {
            if (hi - lo < threshold) {
                return sumSequentially();
            } else {
                int middle = (lo + hi) >> 1;
                Sum left = new Sum(array, lo, middle);
                Sum right = new Sum(array, middle + 1, hi);
                right.fork();
                long leftAns = left.compute(); // 使用原线程
                long rightAns = right.join();
                return leftAns + rightAns;
            }
        }

        private long sumSequentially() {
            return Arrays.stream(array).sum();
        }
    }

    public static void main(String[] args) {
//        int[] array = IntStream.rangeClosed(1, 3).toArray();
//        long sum = Sum.sum(array);
        int array[] = new int[]{1,212,12,12,121,2,54,51,2};
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
        System.out.println(array);
    }
}
