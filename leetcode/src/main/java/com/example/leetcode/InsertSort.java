package com.example.leetcode;

import java.util.Arrays;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/30
 */
public class InsertSort {

    public static int[] sort(int[] source) {
        int[] arr = Arrays.copyOf(source, source.length);

        for (int i = 0; i < arr.length; i++) {
            // 记录要插入的数据
            int tmp = arr[i];
            // 从已经排序的序列最右边开始比较，找到比其更小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] source = {5, 4, 1, 6, 9, 10, 13, 2};
        sort(source);

        sortPeople(11, 3);
    }

    static int sortPeople(int n, int m) {

        int p = 0;
        for (int i = 2; i <= n; i++) {
            p = (p + m) % i;
        }

        System.out.println(p + 1);
        return p + 1;
    }
}
