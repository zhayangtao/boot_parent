package com.calprice.designmodel.bridge;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/14
 * 桥接模式
 */
abstract class Implementor {
    abstract void operation();
}

class ConcreteImplementorA extends Implementor {

    @Override
    void operation() {
        System.out.println("具体实现A的方法执行");
    }
}

class ConcreteImplementorB extends Implementor {

    @Override
    void operation() {
        System.out.println("具体实现B的方法执行");
    }
}

class Abstraction {
    Implementor implementor;

    void setImplementor(Implementor implementor) {
        this.implementor = implementor;
    }

    void operation() {
        implementor.operation();
    }
}

class RefinedAbstraction extends Abstraction {
    @Override
    void operation() {
        implementor.operation();
    }
}

class TestClass {
    public static void main(String[] args) {
        Abstraction abstraction = new RefinedAbstraction();
        abstraction.setImplementor(new ConcreteImplementorA());
        abstraction.operation();

        abstraction.setImplementor(new ConcreteImplementorB());
        abstraction.operation();
    }
}