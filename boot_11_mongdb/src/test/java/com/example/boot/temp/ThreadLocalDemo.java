package com.example.boot.temp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/1/3
 */
public class ThreadLocalDemo {
    public static ThreadLocal<List<String>> threadLocal = new ThreadLocal<>();

    public void setThreadLocal(List<String> values) {
        threadLocal.set(values);
    }

    public void getThreadLocal() {
        System.out.println(Thread.currentThread().getName());
        threadLocal.get().forEach(System.out::println);
    }

    public static void main(String[] args) {
        final ThreadLocalDemo demo = new ThreadLocalDemo();
        new Thread(() -> {
           List<String> params = new ArrayList<>(3);
           params.add("李嵩1");
           params.add("李嵩2");
           params.add("李嵩3");
           demo.setThreadLocal(params);
           demo.getThreadLocal();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                List<String> params = new ArrayList<>(2);
                params.add("chinese");
                params.add("english");
                demo.setThreadLocal(params);
                demo.getThreadLocal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
