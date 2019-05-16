package com.example.boot_akka;

import java.util.Random;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/09
 */
public class LongAccumulatorDemo {
    public static void main(String[] args) throws InterruptedException {
        LongAccumulator accumulator = new LongAccumulator(Long::max, Long.MIN_VALUE);
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(() -> {
                Random random = new Random();
                long value = random.nextLong();
                accumulator.accumulate(value);
            });
            threads[i].start();
        }
        for (int i = 0; i < 1000; i++) {
            threads[i].join();
        }
        System.out.println(accumulator.longValue());
    }
}
