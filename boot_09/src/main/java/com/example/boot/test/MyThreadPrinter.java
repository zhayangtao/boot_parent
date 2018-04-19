package com.example.boot.test;

import java.util.concurrent.TimeUnit;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/09
 * wait 用法
 */
public class MyThreadPrinter implements Runnable {

    private String name;
    private final Object prev;
    private final Object self;

    private MyThreadPrinter(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.println(name);
                    count --;
                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        MyThreadPrinter printer1 = new MyThreadPrinter("A", c, a);
        MyThreadPrinter printer2 = new MyThreadPrinter("B", a, b);
        MyThreadPrinter printer3 = new MyThreadPrinter("C", b, c);

        new Thread(printer1).start();
        TimeUnit.MILLISECONDS.sleep(100);
        new Thread(printer2).start();
        TimeUnit.MILLISECONDS.sleep(100);
        new Thread(printer3).start();
        TimeUnit.MILLISECONDS.sleep(100);

        int cpuNum = Runtime.getRuntime().availableProcessors();// 获取当前 cpu 数目
    }
}
