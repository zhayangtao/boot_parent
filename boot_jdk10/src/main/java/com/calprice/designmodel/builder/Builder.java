package com.calprice.designmodel.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/13
 * 建造者模式
 */
public interface Builder {

    void buildPartA();

    void buildPartB();

    Product getResult();
}

class ConcreteBuilder1 implements Builder {
    private Product product = new Product();

    @Override
    public void buildPartA() {
        product.add("部件A");
    }

    @Override
    public void buildPartB() {
        product.add("部件B");
    }

    @Override
    public Product getResult() {
        return product;
    }
}

class ConcreteBuilder2 implements Builder {
    private Product product = new Product();

    @Override
    public void buildPartA() {
        product.add("部件X");
    }

    @Override
    public void buildPartB() {
        product.add("部件Y");
    }

    @Override
    public Product getResult() {
        return product;
    }
}

class Director {
    void construct(Builder builder) {
        builder.buildPartA();
        builder.buildPartB();
    }
}

class Product {
    List<String> parts = new ArrayList<>();

    void add(String part) {
        parts.add(part);
    }

    public void show() {
        System.out.println("产品创建-----");
        parts.forEach(System.out::println);
    }
}

class TestClass {
    public static void main(String[] args) {
        Director director = new Director();
        Builder builder1 = new ConcreteBuilder1();
        Builder builder2 = new ConcreteBuilder2();

        director.construct(builder1);
        Product product1 = builder1.getResult();
        product1.show();

        director.construct(builder2);
        Product product2 = builder2.getResult();
        product2.show();
    }
}