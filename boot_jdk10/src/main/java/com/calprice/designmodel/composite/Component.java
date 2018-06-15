package com.calprice.designmodel.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/14
 */
abstract class Component {
    String name;

    Component(String name) {
        this.name = name;
    }

    abstract void add(Component c);

    abstract void remove(Component component);

    abstract void display(int depth);
}

class Leaf extends Component {
    public Leaf(String name) {
        super(name);
    }

    @Override
    void add(Component c) {
        System.out.println("cannot add to a leaf");
    }

    @Override
    void remove(Component component) {
        System.out.println("cannot remove from a leaf");
    }

    @Override
    void display(int depth) {
        System.out.println("-" + depth + ' ' + name);
    }
}

class Composite extends Component {
    private List<Component> children = new ArrayList<>();

    public Composite(String name) {
        super(name);
    }

    @Override
    void add(Component c) {
        children.add(c);
    }

    @Override
    void remove(Component component) {
        children.remove(component);
    }

    @Override
    void display(int depth) {
        System.out.println("-" + depth + ' ' +name);
        children.forEach(component -> component.display(depth + 2));
    }
}