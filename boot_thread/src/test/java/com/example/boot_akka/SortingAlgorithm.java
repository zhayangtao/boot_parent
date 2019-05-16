package com.example.boot_akka;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/03
 * 排序算法
 */
public class SortingAlgorithm {
    /**
     * 冒泡排序
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        for (int i = arr.length; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 奇偶交换排序
     * @param arr
     */
    private static void oddEvenSort(int[] arr) {
        int exchFlag = 1, start = 0;
        while (exchFlag == 1 || start == 1) {
            exchFlag = 0;
            for (int i = start; i < arr.length - 1; i+=2) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    exchFlag = 1;
                }
            }
            if (start == 0) {
                start = 1;
            } else {
                start = 0;
            }
        }
    }
}
