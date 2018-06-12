package com.calprice.designmodel.strategy;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/11
 */
public class ConcreteStrategyA extends Strategy {
    @Override
    public void algorithmInterface() {
        System.out.println("算法A");
    }
}

class ConcreteStrategyB extends Strategy {

    @Override
    public void algorithmInterface() {
        System.out.println("算法B");
    }
}

class ConcreteStrategyC extends Strategy {

    @Override
    public void algorithmInterface() {
        System.out.println("算法C");
    }
}

class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void contextInterface() {
        strategy.algorithmInterface();
    }
}