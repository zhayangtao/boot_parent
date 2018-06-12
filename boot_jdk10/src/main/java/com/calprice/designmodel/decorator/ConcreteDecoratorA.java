package com.calprice.designmodel.decorator;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/11
 */
public class ConcreteDecoratorA extends Decorator {
    private String addedState;

    @Override
    public void operation() {
        super.operation();
        addedState = "New State";
        System.out.println("具体装饰对象A的操作");
    }
}

class ConcreteDecoratorB extends Decorator {
    @Override
    public void operation() {
        super.operation();
        addedBehavior();
        System.out.println("具体装饰对象B的操作");
    }

    private void addedBehavior() {
        System.out.println("具体装饰对象A增加的操作");
    }
}
