package com.example.boot.threads;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/20
 */
public class FutureTaskTest {

    public static void main(String[] args) {
        System.out.println("main thread begin at:" + System.nanoTime());

    }

    static class MyTask implements Callable<Integer> {

        private String name;

        @Override
        public Integer call() throws Exception {
            System.out.println("task" + name + "开始进行计算");
            TimeUnit.SECONDS.sleep(3);
            int sum = new Random().nextInt(300);
            int result = 0;
            for (int i = 0; i < sum; i++) {
                result += i;
            }
            return result;
        }
    }
}
