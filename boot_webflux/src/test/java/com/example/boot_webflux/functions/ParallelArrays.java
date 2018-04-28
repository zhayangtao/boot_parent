package com.example.boot_webflux.functions;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/27
 * 并行数组
 */
public class ParallelArrays {

    public static void main(String[] args) {
        long[] arrayOfLong = new long[20000];
        Arrays.parallelSetAll(arrayOfLong, value -> ThreadLocalRandom.current().nextInt(1000000 ));
        Arrays.stream(arrayOfLong).limit(10).forEach(value -> System.out.println(value + " "));
        System.out.println();
        Arrays.parallelSort(arrayOfLong);
        Arrays.stream(arrayOfLong).limit(10).forEach(value -> System.out.println(value + " "));
    }
}
