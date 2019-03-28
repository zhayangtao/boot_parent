package com.example.boot_akka;

import java.util.concurrent.TimeUnit;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/22
 */
public class ThreadGroupName implements Runnable {
    @Override
    public void run() {
        String groupName = Thread.currentThread().getThreadGroup().getName() + "-"
                + Thread.currentThread().getName();
        while (true) {
            System.out.println("I am " + groupName);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("PrintGroup");
        Thread thread = new Thread(group, new ThreadGroupName(), "T1");
        Thread thread2 = new Thread(group, new ThreadGroupName(), "T2");
        thread.start();
        thread2.start();
        System.out.println(group.activeCount());
        group.list();
    }
}
