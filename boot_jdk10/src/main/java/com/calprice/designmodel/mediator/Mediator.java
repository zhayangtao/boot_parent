package com.calprice.designmodel.mediator;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/14
 * 中介者模式：用一个中介对象来封装一些列的对象交互。
 * 中介者使得各个对象不需要显示的相互引用，
 * 从而使其耦合松散，而且可以独立的改变他们之间的交互。
 */
abstract class Mediator {
    abstract void send(String message, Colleague colleague);
}

class ConcreteMediator extends Mediator {

    private ConcreteColleague1 colleague1;
    private ConcreteColleague2 colleague2;

    void setColleague1(ConcreteColleague1 colleague1) {
        this.colleague1 = colleague1;
    }

    void setColleague2(ConcreteColleague2 colleague2) {
        this.colleague2 = colleague2;
    }

    @Override
    void send(String message, Colleague colleague) {
        if (colleague == colleague1) {
            colleague2.notifyC(message);
        } else {
            colleague1.notifyC(message);
        }
    }
}


abstract class Colleague {

    Mediator mediator;
    Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

}

class ConcreteColleague1 extends Colleague {

    ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    void send(String message) {
        mediator.send(message, this);
    }

    void notifyC(String message) {
        System.out.println("colleague1得到消息：" + message);
    }
}

class ConcreteColleague2 extends Colleague {

    ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }

    void send(String message) {
        mediator.send(message, this);
    }

    void notifyC(String message) {
        System.out.println("colleague2得到消息：" + message);
    }
}

class TestClass {

    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();
        ConcreteColleague1 colleague1 = new ConcreteColleague1(mediator);
        ConcreteColleague2 colleague2 = new ConcreteColleague2(mediator);
        mediator.setColleague1(colleague1);
        mediator.setColleague2(colleague2);
        colleague1.send("how are you?");
        colleague2.send("i am fine, fuck you");
    }
}