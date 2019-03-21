package com.example.boot_15.threads;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/02/14
 */
public class MyThread extends Thread {
    private static int num = 0;

    private MyThread() {
        num++;
    }

    @Override
    public void run() {
        System.out.println("创建的第" + num + "个线程");
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();

        MyRunnable runnable = new MyRunnable();
        Thread thread1 = new Thread(runnable);
        thread1.start();
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("子线程ID：" + Thread.currentThread().getId());
    }
}
