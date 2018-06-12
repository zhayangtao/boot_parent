package com.calprice.designmodel.decorator;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/11
 * 装饰器模式
 */
public class Person {
    Person() {
    }

    private String name;

    private Person(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("装扮的" + name);
    }

    public static void main(String[] args) {
        Person xc = new Person("小菜");
        System.out.println("第一种装扮");
        BigTrouser trouser = new BigTrouser();
        TShirts tShirts = new TShirts();

        trouser.decorate(xc);
        tShirts.decorate(trouser);
        tShirts.show();
    }
}

class Finery extends Person {
    private Person component;

    void decorate(Person component) {
        this.component = component;
    }

    @Override
    public void show() {
        if (component != null) {
            component.show();
        }
    }
}

class TShirts extends Finery {
    @Override
    public void show() {
        System.out.println("大T恤");
        super.show();
    }
}

class BigTrouser extends Finery {
    @Override
    public void show() {
        System.out.println("裤子");
        super.show();
    }
}