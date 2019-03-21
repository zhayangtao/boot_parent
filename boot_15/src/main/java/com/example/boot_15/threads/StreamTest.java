package com.example.boot_15.threads;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/12
 */
public class StreamTest {

    public static void testCreateStream() {
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> stringStream = list.stream();
        list.parallelStream();

        Arrays.stream(new int[]{1, 2, 3});
        Stream.of(1, 2, 3, 4);
        IntStream.of(1, 2, 3);

        // 迭代无限流
        Stream.iterate(1, integer -> integer + 1).limit(100).forEach(System.out::println);
        // 生成无限流
        Stream.generate(Math::random).limit(100).forEach(System.out::println);
    }

    public static void testStream() {
        Stream.of(1, 2, 1, 3, 3, 2, 4).filter(integer -> integer % 2 == 0).forEach(System.out::print);
        System.out.println();
        Stream.of(1, 2, 1, 3, 3, 2, 4).filter(integer -> integer % 2 == 0).distinct().forEach(System.out::print);
        System.out.println();
        Stream.of(1, 2, 1, 3, 3, 2, 4).filter(integer -> integer % 2 == 0).skip(2).forEach(System.out::print);

        // 并行流
        Stream.of(1, 2, 1, 3, 3, 2, 4).parallel();
    }

    public static void main(String[] args) {
        testStream();
    }
}
