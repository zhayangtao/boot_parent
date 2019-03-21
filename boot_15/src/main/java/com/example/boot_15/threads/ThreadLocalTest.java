package com.example.boot_15.threads;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/02/18
 */
public class ThreadLocalTest {
    private ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();
    private ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    private void set() {
        longThreadLocal.set(Thread.currentThread().getId());
        stringThreadLocal.set(Thread.currentThread().getName());
    }

    private long getLong() {
        return longThreadLocal.get();
    }

    public String getString() {
        return stringThreadLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest threadLocalTest = new ThreadLocalTest();

        threadLocalTest.set();

        System.out.println(threadLocalTest.getLong());
        System.out.println(threadLocalTest.getString());

        Thread thread = new Thread(() -> {
            threadLocalTest.set();
            System.out.println(threadLocalTest.getLong());
            System.out.println(threadLocalTest.getString());
        });
        thread.start();
        thread.join();

        System.out.println(threadLocalTest.getLong());
        System.out.println(threadLocalTest.getString());
    }
}
