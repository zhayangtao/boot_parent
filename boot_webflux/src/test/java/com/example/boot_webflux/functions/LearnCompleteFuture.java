package com.example.boot_webflux.functions;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/28
 * java 8 新增的 CompleteFuture
 * 需求：在两个线程里并行执行任务A和任务B，只要有一个任务完成了，就执行任务C
 */
public class LearnCompleteFuture {
    private static Random random = new Random();

    /**
     * 使用 java 原始的 Future
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void useFuture() throws ExecutionException, InterruptedException {
        System.out.println("Future");
        ExecutorService service = Executors.newFixedThreadPool(3);
        Future<Void> futureA = service.submit(() -> work("A1"));
        Future<Void> futureB = service.submit(() -> work("B1"));
        while (true) {
            try {
                futureA.get(1, TimeUnit.SECONDS);
                break;
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
            try {
                futureB.get(1, TimeUnit.SECONDS);
                break;
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        }
        service.submit(() -> work("C1")).get();
        service.shutdown(); // 记得关闭 service
    }

    /**
     * 使用 java 8 中的 CompletableFuture
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void useCompletableFuture() throws ExecutionException, InterruptedException {
        System.out.println("CompletableFuture");
        CompletableFuture<Void> futureA = CompletableFuture.runAsync(() -> work("A2"));
        CompletableFuture<Void> futureB = CompletableFuture.runAsync(() -> work("B2"));
        futureA.runAfterEither(futureB, () -> work("C2")).get();
    }

    /**
     * 简化版 CompletableFuture
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void useCompletableFutureSimple() throws ExecutionException, InterruptedException {
        System.out.println("CompletableFutureSimple");
        CompletableFuture.runAsync(() -> work("A3"))
                .runAfterEither(CompletableFuture.runAsync(() -> work("B3")),
                        () -> work("C3")).get();
    }

    private static Void work(String name) {
        System.out.println(name + " starts at " + LocalTime.now());
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " ends at " + LocalTime.now());
        return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        useFuture();
        TimeUnit.SECONDS.sleep(10);
        System.out.println();
        useCompletableFuture();
        System.out.println();
        useCompletableFutureSimple();
    }
}
