package com.example.boot.utils;

import org.junit.Test;

import java.util.Optional;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/11/09
 */
public class TestJdk {

    public int add(int x, int y) {
        return x + y;
    }

    public void test1() {
        new Thread(() -> System.out.println("In java8"));
    }

    @Test
    public void test2() {
        Integer value1 = null;
        Integer value2 = 10;

        Optional<Integer> a = Optional.ofNullable(value1);
        Optional<Integer> b = Optional.of(value2);
        System.out.println(sum(a, b));
    }

    private Integer sum(Optional<Integer> a, Optional<Integer> b) {
        System.out.println(a.isPresent());
        System.out.println(b.isPresent());
        System.out.println(a.isEmpty());
        Integer value1 = a.orElse(0);
        Integer value2 = b.get();
        return value1 + value2;
    }
}
