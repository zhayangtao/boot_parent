package com.calprice.designmodel.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/13
 * 观察者模式 / 发布-订阅模式
 */
public interface Observer {

    void update();
}

class ConcreteObserver implements Observer {

    private String name;
    private String observerState;
    private ConcreteSubject subject;

    public ConcreteObserver(String name, ConcreteSubject subject) {
        this.name = name;
        this.subject = subject;
    }

    @Override
    public void update() {
        observerState = subject.getSubjectState();
        System.out.printf("观察者%s的新状态是%s\n", name, observerState);
    }
}

abstract class Subject {
    List<Observer> observers = new ArrayList<>();

    void attach(Observer observer) {
        observers.add(observer);
    }

    void detach(Observer observer) {
        observers.remove(observer);
    }

    void notifyAllO() {
        observers.forEach(Observer::update);
    }
}

class ConcreteSubject extends Subject {
    private String subjectState;

    public String getSubjectState() {
        return subjectState;
    }

    public void setSubjectState(String subjectState) {
        this.subjectState = subjectState;
    }
}

class TestClass {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.attach(new ConcreteObserver("X", subject));
        subject.attach(new ConcreteObserver("Y", subject));
        subject.attach(new ConcreteObserver("Z", subject));
        subject.setSubjectState("ABC");
        subject.notifyAllO();
        System.out.println();
    }
}