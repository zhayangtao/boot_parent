package com.example.boot_akka;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/22
 */
public class JoinMain {
    private volatile static int i = 0;
    private static class AddThread extends Thread {
        @Override
        public void run() {
            for (i = 0; i < 100000000; i++) {

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread addThread = new AddThread();
        addThread.start();
        addThread.join();
        System.out.println(i);
    }
}
