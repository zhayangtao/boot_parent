package com.example.boot_webflux;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/04
 */
public class AbstractTest {
    public static void main(String[] args) {
        Shape shape = new Triangle("黑色", 3, 4, 5);
        System.out.println(shape.getType());
        System.out.println(shape.cal());
    }
}

abstract class Shape {
    {
        System.out.println("执行 shape 的初始化块");
    }
    private String color;
    public abstract double cal();
    public abstract String getType();

    Shape(String color) {
        System.out.println("执行 shape 的构造器");
        this.color = color;
    }

    public Shape() {
    }
}

class Triangle extends Shape {
    private double a;
    private double b;
    private double c;

    Triangle(String color, double a, double b, double c) {
        super(color);
        System.out.println("执行 triangle 的构造器");
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public void setSides(double a, double b, double c) {
        if (a >= b + c || b >= a + c || c >= a + b) {
            System.out.println("error");
            return;
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double cal() {
        return a + b + c;
    }

    @Override
    public String getType() {
        return "三角形";
    }
}

interface Output {
    int MAX_CACHE_LINE = 50;
    void out();
    void getData(String msg);
    // java 8
    default void print(String... msgs) {
        for (String msg : msgs) {
            System.out.println(msg);
        }
    }
    // java 8
    static String staticTest() {
        return "";
    }
}

class Cow {
    private double weight;
    public Cow() {
    }

    public Cow(double weight) {
        this.weight = weight;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    private class CowLeg {
        private double length;
        private String color;

        void info() {
            System.out.println("color:" + color + ",length:" + length + ",weight:" + weight);
        }
    }
    public void test() {
        CowLeg c1 = new CowLeg(1.12, "black_white");
        c1.info();
    }
}
