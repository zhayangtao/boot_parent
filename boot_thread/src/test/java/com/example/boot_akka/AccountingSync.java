package com.example.boot_akka;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/22
 */
public class AccountingSync implements Runnable {
    private static AccountingSync instance = new AccountingSync();
    private static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            synchronized (instance) {
                i++;
            }
        }
    }

    private static class AccountSync2 implements Runnable {
        private static AccountSync2 instance = new AccountSync2();
        private static int i = 0;

        synchronized void increase() {
            i++;
        }

        @Override
        public void run() {
            for (int j = 0; j < 10000000; j++)
                increase();
            }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(AccountSync2.instance);
        Thread thread1 = new Thread(AccountSync2.instance);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(AccountSync2.i);
    }
}
