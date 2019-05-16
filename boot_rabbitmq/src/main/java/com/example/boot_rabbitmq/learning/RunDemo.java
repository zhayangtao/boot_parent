package com.example.boot_rabbitmq.learning;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/26
 */
public class RunDemo {
    public static void main(String[] args) {
        Random random = new Random();
        // 随机产生数据
        Stream<Integer> stream = Stream.generate(random::nextInt).limit(2)
                .peek(integer -> print("peek: " + integer)) // 第一个无状态操作
                .filter(integer -> { // 第二个无状态操作
                    print("filter: " + integer);
                    return integer > 1000000;
                })
                .sorted((o1, o2) -> { // 有状态操作
                    print("排序：" + o1 + ", " + o2);
                    return o1.compareTo(o2);
                })
                .peek(integer -> { //无状态操作
                    print("peek2: " + integer);
                }).parallel();
        stream.count(); // 终止操作

    }

    private static void print(String s) {
        System.out.println(Thread.currentThread().getName() + " > " + s);
        try {
            TimeUnit.MILLISECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
