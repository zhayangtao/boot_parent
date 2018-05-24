package com.example.boot_webflux;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/05
 */
public class CommandTest {
    public static void main(String[] args) {

    }
}

@FunctionalInterface
interface Eatable {
    void taste();
}
@FunctionalInterface
interface Flyable {
    void fly(String weather);
}
@FunctionalInterface
interface Addable {
    int add(int a, int b);
}

class LambdaQs {
    private void eat(Eatable eatable) {
        System.out.println(eatable);
        eatable.taste();
    }

    private void drive(Flyable flyable) {
        System.out.println("driving:" + flyable);
        flyable.fly("碧空如洗");
    }

    private void test(Addable addable) {
        System.out.println("5 + 3=" + addable.add(5, 3));
    }

    public static void main(String[] args) {
        LambdaQs lambdaQs = new LambdaQs();
        lambdaQs.eat(() -> System.out.println("nice apple"));
        lambdaQs.drive(weather -> {
            System.out.println("weather:" + weather);
            System.out.println("driving nice");
        });
        lambdaQs.test((a, b) -> a + b);

        //
        Runnable runnable = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println();
            }
        };
        Object object = (Runnable) () -> {

        };

        String[] arr1 = new String[]{"java", "kafa", "ios"};
        Arrays.parallelSort(arr1, Comparator.comparingInt(String::length));
    }

    enum SeasonEnum {
        SPRING, SUMMER, FALL, WINTER;
    }

    enum Gender {
        MALE, FEMALE;
        public String name;
    }

    void testEnum() {
        Gender gender = Enum.valueOf(Gender.class, "FEMALE");
        gender.name = "女";
        System.out.println(gender.name);
    }
}
