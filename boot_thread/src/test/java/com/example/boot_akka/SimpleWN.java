package com.example.boot_akka;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/22
 */
public class SimpleWN {
    final static Object object = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T1 start!");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end");
            }

        }
    }
    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T2 start, notify one thread");
                object.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new T1();
        Thread thread1 = new T2();
        thread.start();
        thread1.start();
    }

}
