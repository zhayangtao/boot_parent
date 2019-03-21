package com.example.boot_15.threads;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/02/15
 */
public class JoinTest {

    private class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("进入线程" + Thread.currentThread().getName());
            try {
                sleep(5000);
            } catch (InterruptedException e) {

            }
            System.out.println("线程" + Thread.currentThread().getName() + "执行完毕");
        }
    }

}
