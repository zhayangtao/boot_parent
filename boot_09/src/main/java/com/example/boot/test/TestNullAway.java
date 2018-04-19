package com.example.boot.test;

import shadow.checkerframework.checker.nullness.qual.Nullable;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/13
 */
public class TestNullAway {

    static void log(@Nullable Object x) {
        System.out.println(x.toString());
    }

    static void foo() {
        log(null);
    }
}
