package com.calprice.designmodel.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/14
 * 享元模式
 */
interface Flyweight {
    void operation(int extrinsicstate);
}

class ConcreteFlyweight implements Flyweight {
    @Override
    public void operation(int extrinsicstate) {
        System.out.println("具体 Flyweight:" + extrinsicstate);
    }
}

class UnsharedConcreteFlyweight implements Flyweight {
    @Override
    public void operation(int extrinsicstate) {
        System.out.println("不共享的具体 Flyweight:" + extrinsicstate);
    }
}

// 享元工厂
class FlyweightFactory {
    private Map<String, Flyweight> flyweightMap = new HashMap<>();

    FlyweightFactory() {
        flyweightMap.put("x", new ConcreteFlyweight());
        flyweightMap.put("y", new ConcreteFlyweight());
        flyweightMap.put("z", new ConcreteFlyweight());
    }

    Flyweight getFlyweight(String key) {
        return flyweightMap.get(key);
    }
}

class TestClass {
    public static void main(String[] args) {
        int extrinsicstate = 22;

        FlyweightFactory factory = new FlyweightFactory();
        Flyweight flyweight1 = factory.getFlyweight("x");
        flyweight1.operation(extrinsicstate);

        Flyweight flyweight2 = factory.getFlyweight("y");
        flyweight2.operation(extrinsicstate);

        Flyweight flyweight3 = factory.getFlyweight("z");
        flyweight3.operation(extrinsicstate);

        UnsharedConcreteFlyweight flyweight = new UnsharedConcreteFlyweight();
        flyweight.operation(extrinsicstate);

    }
}