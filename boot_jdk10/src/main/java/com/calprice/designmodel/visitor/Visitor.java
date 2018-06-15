package com.calprice.designmodel.visitor;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/15
 */
abstract class Visitor {

    abstract void visitConcreteElementA(ConcreteElementA concreteElementA);
    abstract void visitConcreteElementB(ConcreteElementB concreteElementB);
}

class ConcreteVisitor1 extends Visitor {

    @Override
    void visitConcreteElementA(ConcreteElementA concreteElementA) {
        System.out.printf("%s被%s访问", concreteElementA.getClass().getName(), this.getClass().getName());
    }

    @Override
    void visitConcreteElementB(ConcreteElementB concreteElementB) {
        System.out.printf("%s被%s访问", concreteElementB.getClass().getName(), this.getClass().getName());
    }

}

class ConcreteVisitor2 extends Visitor {

    @Override
    void visitConcreteElementA(ConcreteElementA concreteElementA) {
        System.out.printf("%s被%s访问", concreteElementA.getClass().getName(), this.getClass().getName());
    }

    @Override
    void visitConcreteElementB(ConcreteElementB concreteElementB) {
        System.out.printf("%s被%s访问", concreteElementB.getClass().getName(), this.getClass().getName());
    }

}


class ConcreteElementA {
}
class ConcreteElementB {

}