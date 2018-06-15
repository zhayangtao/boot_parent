package com.calprice.designmodel.memento;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/14
 * 备忘录模式
 */
public class Originator {

    private String state;

    public String getState() {
        return state;
    }

    void setState(String state) {
        this.state = state;
    }

    Memento createMemento() {
        return new Memento(state);
    }

    void setMemento(Memento memento) {
        state = memento.getState();
    }

    void show() {
        System.out.println("state=" + state);
    }
}

// 备忘录类
class Memento {

    private String state;

    Memento(String state) {
        this.state = state;
    }

    String getState() {
        return state;
    }
}

// 备忘录管理者
class CareTaker {

    private Memento memento;

    Memento getMemento() {
        return memento;
    }

    void setMemento(Memento memento) {
        this.memento = memento;
    }
}

class TestClass {

    public static void main(String[] args) {
        Originator o = new Originator();
        o.setState("On");
        o.show();

        CareTaker careTaker = new CareTaker();
        careTaker.setMemento(o.createMemento());
        o.setState("Off");
        o.show();

        o.setMemento(careTaker.getMemento());
        o.show();
    }

}