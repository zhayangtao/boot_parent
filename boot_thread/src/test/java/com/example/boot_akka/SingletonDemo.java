package com.example.boot_akka;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/29
 * 单例
 */
public class SingletonDemo {
    public static int STATUS = 1;

    private SingletonDemo() {
        System.out.println("Singleton is created");
    }

    private static SingletonDemo instance = new SingletonDemo();

    public static SingletonDemo getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println("main");
        int i1 = SingletonDemo.STATUS;
        i1 = SingletonDemo.STATUS;
        i1 = SingletonDemo.STATUS;
        i1 = SingletonDemo.STATUS;
        i1 = SingletonDemo.STATUS;
        i1 = SingletonDemo.STATUS;
    }
}
