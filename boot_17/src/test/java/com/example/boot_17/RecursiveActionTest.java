package com.example.boot_17;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/21
 */
public class RecursiveActionTest {
    static class Sorter extends RecursiveAction {
        private final long[] array;
        private final int lo, hi;
        private static final int THEREHOLD = 1000;

        private Sorter(long[] array, int lo, int hi) {
            this.array = array;
            this.lo = lo;
            this.hi = hi;
        }


        private static void sort(long[] array) {
            ForkJoinPool.commonPool().invoke(new Sorter(array, 0, array.length));
        }

        @Override
        protected void compute() {
            if (hi - lo < THEREHOLD) {
                Arrays.sort(array, lo, hi);
            } else {
                int mid = (lo + hi) >> 1; // 符号右移
                Sorter left = new Sorter(array, lo, mid);
                Sorter right = new Sorter(array, mid + 1, hi);
                invokeAll(left, right);
                merge(lo, mid, hi);
            }
        }

        private void merge(int lo, int mid, int hi) {
            long[] buf = Arrays.copyOfRange(array, lo, mid);
            for (int i = 0, k = mid; i < buf.length; i++) {
                if (k == hi || buf[i] < array[k]) {
                    array[lo] = buf[i++];
                } else {
                    array[lo] = array[k++];
                }
            }
        }
    }

    public static void main(String[] args) {
        int i = 8;
        int i1 = -8;
        System.out.println(i >>> 1);
        System.out.println(i >> 1);

        System.out.println(i1 >>> 1);
        System.out.println(i1 >> 1);

        long[] array = new Random().longs(100_0000).toArray();
        Sorter.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
