package com.example.boot_akka;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/09
 */
public class ConstrMethodRef {

    private static final int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,};

    @FunctionalInterface
    interface UserFactory<U extends User> {
        U create(int id, String name);
    }

    private static UserFactory<User> userFactory = User::new;

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            users.add(userFactory.create(i, "billy" + i));
        }
        users.stream().map(User::getName).forEach(System.out::println);
    }

    public void test () {
        Arrays.stream(arr).forEach(System.out::println);
    }

    public static class AskThread implements Runnable {

        CompletableFuture<Integer> re;

        AskThread(CompletableFuture<Integer> re) {
            this.re = re;
        }

        @Override
        public void run() {
            int myRe = 0;
            try {
                myRe = re.get() * re.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(myRe);
        }

        public static void main(String[] args) throws InterruptedException {
            final CompletableFuture<Integer> future = new CompletableFuture<>();
            new Thread(new AskThread(future)).start();
            // 模拟长时间计算过程
            TimeUnit.SECONDS.sleep(1);
            future.complete(60);
        }
    }

    public static Integer calc(Integer para) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para * para;
    }

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        final CompletableFuture<Integer> future = supplyAsync(() -> calc(50));
        System.out.println(future.get());
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = supplyAsync(() -> calc(50))
                .thenApply(integer -> Integer.toString(integer))
                .thenApply(s -> "\"" + s + "\"")
                .thenAccept(System.out::println);
        future.get();
    }

    @Test
    public void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = supplyAsync(() -> calc(50))
                .exceptionally(throwable -> {
                    System.out.println(throwable.toString());
                    return 0;
                })
//                .thenApply(integer -> Integer.toString(integer))
                .thenApply(s -> "\"" + s + "\"")
                .thenAccept(System.out::println);
        future.get();
    }

    @Test
    public void test4() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> calc(50))
                .thenCompose(integer -> CompletableFuture.supplyAsync(() -> calc(integer)))
                .thenApply(s -> "\"" + s + "\"")
                .thenAccept(System.out::println);
        future.get();
    }
}
