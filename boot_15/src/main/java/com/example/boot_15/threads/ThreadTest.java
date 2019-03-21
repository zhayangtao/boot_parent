package com.example.boot_15.threads;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/02/15
 */
public class ThreadTest {

    private int i = 10;
    private Object object = new Object();


    private class MyThread extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                i++;
                System.out.println("i:" + i);

                try {
                    System.out.println("线程" + Thread.currentThread().getName() + "进入睡眠状态");
                    sleep(1000); // sleep 方法不会释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "睡眠结束");
                i++;
                System.out.println("i:" + i);
            }
        }
    }

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        MyThread thread1 = threadTest.new MyThread();
        MyThread thread2 = threadTest.new MyThread();

        thread1.start();
        thread2.start();
    }

}


