package com.example.boot_akka;

import java.util.concurrent.locks.LockSupport;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/27
 */
public class LockSupportDemo {
    private static final Object u = new Object();
    private static ChangeObjectThread thread1 = new ChangeObjectThread("t1");
    private static ChangeObjectThread thread2 = new ChangeObjectThread("t2");

    private static class ChangeObjectThread extends Thread {
        ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        thread1.start();
        Thread.sleep(100);
        thread2.start();
        LockSupport.unpark(thread1);
        LockSupport.unpark(thread2);
        thread1.join();
        thread2.join();
    }
}
