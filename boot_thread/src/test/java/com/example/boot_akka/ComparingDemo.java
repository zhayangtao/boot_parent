package com.example.boot_akka;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/08
 */
public interface ComparingDemo<T> {
    int num = 2;

    default void test() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbers.forEach(System.out::println);

        Function<Integer, Integer> stringConverter = integer -> integer * num;
        System.out.println(stringConverter.apply(3));

        numbers.stream().map(Double::toString).forEach(System.out::println);
    }

    static void main(String[] args) {

    }


}
