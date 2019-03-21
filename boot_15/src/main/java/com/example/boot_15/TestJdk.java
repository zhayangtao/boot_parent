package com.example.boot_15;

import org.junit.Test;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/11/09
 */
public class TestJdk {

    @Test
    public void test1() {
        new Thread(() -> System.out.println(""));
    }
}
