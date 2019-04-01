package com.example.boot_akka;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/29
 * 单例延迟加载（懒汉模式）
 */
public class SingletonLazyDemo {

    private SingletonLazyDemo() {
        System.out.println("SingletonLazyDemo is created");
    }

    private static SingletonLazyDemo instance = null;

    public static synchronized SingletonLazyDemo getInstance() {
        if (instance == null) {
            instance = new SingletonLazyDemo();
        }
        return instance;
    }
}
