package com.calprice.designmodel.adapter;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/13
 * 适配器模式
 */
public class Adapter extends Target{

    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}

class Target {
    public void request() {
        System.out.println("普通请求");
    }
}

class Adaptee {
    void specificRequest() {
        System.out.println("特殊请求");
    }
}

class TestClass {
    public static void main(String[] args) {
        Target target = new Adapter();
        target.request();
    }
}