package com.calprice.designmodel.facade;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/13
 * 外观模式
 */
public class Facade {
    private SubSystemOne one;
    private SubSystemTwo two;
    private SubSystemThree three;
    private SubSystemFour four;

    private Facade() {
        one = new SubSystemOne();
        two = new SubSystemTwo();
        three = new SubSystemThree();
        four = new SubSystemFour();
    }

    private void methodA() {
        System.out.println("方法组A-----");
        one.methodOne();
        two.methodTwo();
        three.methodThree();
        four.methodFour();
    }

    private void methodB() {
        System.out.println("方法组B-----");
        two.methodTwo();
        three.methodThree();
    }

    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.methodA();
        facade.methodB();
    }

}

class SubSystemOne {
    void methodOne() {
        System.out.println("子系统方法一");
    }
}

class SubSystemTwo {
    void methodTwo() {
        System.out.println("子系统方法二");
    }
}

class SubSystemThree {
    void methodThree() {
        System.out.println("子系统方法三");
    }
}

class SubSystemFour {
    void methodFour() {
        System.out.println("子系统方法四");
    }
}