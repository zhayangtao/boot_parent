package com.calprice.designmodel.chain;

import java.util.Arrays;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/14
 * 责任链模式
 */
abstract class Handler {
    Handler successor;

    void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    abstract void handleRequest(int request);
}

class ConcreteHandler1 extends Handler {

    @Override
    void handleRequest(int request) {
        if (request >= 0 && request < 10) {
            System.out.printf("%s 处理请求 %s\n", this.getClass().getName(), request);
        } else if (successor != null) { // 移交到下一位
            successor.handleRequest(request);
        }
    }
}

class ConcreteHandler2 extends Handler {

    @Override
    void handleRequest(int request) {
        if (request >= 10 && request < 20) {
            System.out.printf("%s 处理请求 %s\n", this.getClass().getName(), request);
        } else if (successor != null) { // 移交到下一位
            successor.handleRequest(request);
        }
    }
}

class ConcreteHandler3 extends Handler {

    @Override
    void handleRequest(int request) {
        if (request >= 20 && request < 30) {
            System.out.printf("%s 处理请求 %s\n", this.getClass().getName(), request);
        } else if (successor != null) { // 移交到下一位
            successor.handleRequest(request);
        }
    }
}

class TestClass {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();
        handler1.setSuccessor(handler2);
        handler2.setSuccessor(handler3);

        int[] requests = {2, 5, 14, 22, 18, 3, 27, 20};
        Arrays.stream(requests).forEach(handler1::handleRequest);
    }
}