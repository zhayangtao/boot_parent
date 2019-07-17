package com.example.boot_start_learning.chapter5_spring_aop;

import java.util.Random;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 */
public class KeyGenerator {
    static final long WEAK_KEY = 0xFFFFFF0000000L;
    private static final long STRONG_KEY = 0xACDF03F590AE56L;
    private Random random = new Random();

    long getKey() {
        int x = random.nextInt(3);
        if (x == 1) {
            return WEAK_KEY;
        }
        return STRONG_KEY;
    }
}
