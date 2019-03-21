package com.example.boot_17;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/19
 */
public class CountDownTest {
    static class Player implements Runnable {
        CountDownLatch latch;

        Player(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println("Player[" + name + "] is waiting for the whistle");
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Player[" + name + "] is running");
            int time = RandomUtils.nextInt(5000, 9000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Player[" + name + "] has arrived");
        }
    }

    static class Referee implements Runnable {
        CountDownLatch latch;

        Referee(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println("Referee[" + name + "] is ready");
            latch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        int num = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Player(latch));
        }
        Thread.sleep(1000);
        new Thread(new Referee(latch)).start();
        Thread.sleep(10000);
        executorService.shutdown();
    }

    static class MachineLion implements Runnable {
        private String name;
        CountDownLatch latch;

        public MachineLion(String name, CountDownLatch latch) {
            this.name = name;
            this.latch = latch;
        }

        @Override
        public void run() {
            int time = RandomUtils.nextInt(0, 2000);
            System.out.println(Thread.currentThread().getName() + "[" + name + "]正在运行组装");
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " 我来组成[" + name + "]!");
        }
    }
}
