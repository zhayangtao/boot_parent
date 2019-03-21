package com.example.boot_15.threads;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/11
 */
public class LambdaTest {

    public void test1() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        };

        runnable.run();
        System.out.println("***********************");
        Runnable runnable1 = () -> System.out.println("Hello Lambda");
        runnable1.run();
    }

    public void test2() {
        Consumer<String> consumer = s -> System.out.println("lambda " + s);
        consumer.accept("Hello");
    }

    public void test3() {
        Comparator<Integer> comparator = (o1, o2) -> {
            System.out.println("比较 o1, o2");
            return Integer.compare(o1, o2);
        };
        System.out.println(comparator.compare(1, 2));
    }

    public void toHappy(Double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    public void test() {
        this.toHappy(1000D, System.out::println);
        String msgA = "Hello";
        String msgB = "World";
        String msgC = "Java";

        log(1, (msg) -> msgA + msgB);
    }

    private static void log(int level, MessageBuilder builder) {
        if (level == 1) {//只有当条件满足才会执行Lambda表达式中的拼接
            System.out.println(builder.buildMessage());
        }
    }

    @FunctionalInterface
    interface MessageBuilder {
        String buildMessage(String... msgs);
    }
}


