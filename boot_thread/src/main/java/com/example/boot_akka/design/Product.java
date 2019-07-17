package com.example.boot_akka.design;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/05/23
 * 建造者模式
 */
public class Product {
    private List<String> productParts = new ArrayList<>();

    public void add(String part) {
        productParts.add(part);
    }

    public void show() {
        productParts.forEach(System.out::println);
    }
}
