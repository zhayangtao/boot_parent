package com.example.boot_13_redis;

import org.junit.Test;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/09
 */
public class QuickSort {

    public void testQuickSort(int[] a, int left, int right) {
        int i, j, t, base;
        if (left > right) {
            return;
        }
        base = a[left];
        i = left;
        j = right;
        while (i != j) {
            // 从右边开始找
            while (a[j] >= base && i < j) {
                j--;
            }
            //
            while (a[i] <= base && i < j) {
                i++;
            }
            if (i < j) {
                t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        // 将基准数归位
        a[left] = a[i];
        a[i] = base;
        testQuickSort(a, left, i - 1); // 处理左边的
        testQuickSort(a, i + 1, right); // 处理右边的
    }
}
