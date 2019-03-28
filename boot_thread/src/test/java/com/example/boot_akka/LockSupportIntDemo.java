package com.example.boot_akka;

import java.util.concurrent.locks.LockSupport;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/27
 */
public class LockSupportIntDemo {
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
                if (Thread.interrupted()) {
                    System.out.println(getName() + " 被中断了");
                }
            }
            System.out.println(getName() + " 执行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        thread1.start();
        Thread.sleep(100);
        thread2.start();
        thread1.interrupt();
        LockSupport.unpark(thread2);
    }

}
