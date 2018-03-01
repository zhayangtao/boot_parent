package com.example.boot.utils;

import java.util.*;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/1/3
 */
public class SimpleThreadLocal<T> {
    private Map<Thread, T> valueMap = Collections.synchronizedMap(new HashMap<Thread, T>());

    public void set(T value) {
        valueMap.put(Thread.currentThread(), value);
    }

    public T get() {
        Thread currentThread = Thread.currentThread();
        T t = valueMap.get(currentThread);
        if (t == null && !valueMap.containsKey(currentThread)) {
            valueMap.put(currentThread, t);
        }
        return t;
    }

    public void remove() {
        valueMap.remove(Thread.currentThread());
    }

    public T initialValue() {
        return null;
    }

    public static void main(String[] args) {
        SimpleThreadLocal<List<String>> threadLocal = new SimpleThreadLocal<>();
        new Thread(() -> {
            List<String> params = new ArrayList<>(3);
            params.add("张三");
            params.add("李四");
            params.add("王五");
            threadLocal.set(params);
            System.out.println(Thread.currentThread().getName());
            threadLocal.get().forEach(System.out::println);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                List<String> params = new ArrayList<>(2);
                params.add("Chinese");
                params.add("English");
                threadLocal.set(params);
                System.out.println(Thread.currentThread().getName());
                threadLocal.get().forEach(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
