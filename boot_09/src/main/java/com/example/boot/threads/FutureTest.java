package com.example.boot.threads;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/20
 */
public class FutureTest {
    public static void main(String[] args) {
        System.out.println("main thread begin at:" + System.nanoTime());
        ExecutorService service = Executors.newCachedThreadPool();
        HandleCallable callable1 = new HandleCallable("1");
        HandleCallable callable2 = new HandleCallable("2");
        HandleCallable callable3 = new HandleCallable("3");
        Future<Integer> result1 = service.submit(callable1);
        Future<Integer> result2 = service.submit(callable2);
        Future<Integer> result3 = service.submit(callable3);
        service.shutdown();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("task1结果：" + result1.get());
            System.out.println("task2结果：" + result2.get());
            System.out.println("task3结果：" + result3.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("main thread finish at:" + System.nanoTime());
    }

    static class HandleCallable implements Callable<Integer> {

        private String name;

        HandleCallable(String name) {
            this.name = name;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("task:" + name + "开始进行计算");
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
