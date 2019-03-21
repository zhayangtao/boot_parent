package com.example.boot_17;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/15
 */
public class SemaphoreThread {
    private int a = 0;

    class Bank {
        private int account = 100;

        public int getAccount() {
            return account;
        }

        public void save(int money) {
            account += money;
        }
    }

    class MyThread implements Runnable {
        private Bank bank;
        private Semaphore semaphore; // 信号量

        public MyThread(Bank bank, Semaphore semaphore) {
            this.bank = bank;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            int b = a++;
            if (semaphore.availablePermits() > 0) {
                System.out.println("线程" + b + "启动，进入银行,有位置立即去存钱");
            } else
                System.out.println("线程" + b + "启动，进入银行,无位置，去排队等待等待");
            try {
                semaphore.acquire();
                bank.save(10);
                System.out.println(b + "账户余额为：" + bank.getAccount());
                TimeUnit.SECONDS.sleep(1);
                System.out.println("线程" + b + "存钱完毕，离开银行");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void useThread() {
        Bank bank = new Bank();
        Semaphore semaphore = new Semaphore(2);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.submit(new Thread(new MyThread(bank, semaphore)));
        }

        service.shutdown();

        semaphore.acquireUninterruptibly(2);//获取两个许可，获得许可之前，阻塞线程
        System.out.println("到点了，工作人员要吃饭了");
        // 释放两个许可，并将其返回给信号量
        semaphore.release(2);
    }

    public static void main(String[] args) {
        SemaphoreThread test = new SemaphoreThread();
        test.useThread();
    }
}
