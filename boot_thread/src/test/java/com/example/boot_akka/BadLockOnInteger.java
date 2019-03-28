package com.example.boot_akka;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/25
 */
public class BadLockOnInteger implements Runnable{
    private static Integer i = 0;
    private static BadLockOnInteger instance = new BadLockOnInteger();

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            synchronized (i) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println(i);
    }
}
