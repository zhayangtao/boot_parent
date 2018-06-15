package com.calprice.designmodel.state;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/13
 * 状态模式：当一个对象的内在状态改变时允许改变其行为，
 * 这个对象看起来像是改变了其类
 */
public interface State {
    void handle(Context context);
}

class ConcreteStateA implements State {

    @Override
    public void handle(Context context) {
        context.setState(new ConcreteStateB());
    }
}

class ConcreteStateB implements State {

    @Override
    public void handle(Context context) {
        context.setState(new ConcreteStateA());
    }
}

class Context {
    private State state;

    Context(State state) {
        this.state = state;
    }

    void setState(State state) {
        this.state = state;
        System.out.println("当前状态：" + state.getClass().getName());
    }

    void request() {
        state.handle(this);
    }
}

class TestClass {
    public static void main(String[] args) {
        Context context = new Context(new ConcreteStateA());
        context.request();
        context.request();
        context.request();
        context.request();
    }
}