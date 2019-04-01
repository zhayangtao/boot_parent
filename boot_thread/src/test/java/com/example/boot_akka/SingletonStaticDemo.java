package com.example.boot_akka;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/29
 */
public class SingletonStaticDemo {
    private SingletonStaticDemo() {
        System.out.println("SingletonStaticDemo is created");
    }

    private static class SingletonStaticDemoHolder {
        private static SingletonStaticDemo instance = new SingletonStaticDemo();
    }

    public static SingletonStaticDemo getInstance() {
        return SingletonStaticDemoHolder.instance;
    }
}
