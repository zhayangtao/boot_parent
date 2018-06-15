package com.calprice.designmodel.prototype;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/12
 * 原型模式
 */
public class Prototype implements Cloneable{

    private int x;
    private int y;
    private int z;

    private Prototype() {
        this.x = 2;
        this.y = 3;
        this.z = 4;
    }

    private void change() {
        this.x = 9;
        this.y = 8;
        this.z = 7;
    }

    public Prototype clone() {
        Object object = null;
        try {
            object = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (Prototype) object;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("x", x)
                .append("y", y)
                .append("z", z)
                .toString();
    }

    public static void main(String[] args) {
        Prototype prototype1 = new Prototype();
        prototype1.change();
        System.out.println(prototype1);

        Prototype prototype2 = prototype1.clone();
        System.out.println(prototype2);
    }
}

class Field implements Cloneable {
    private int a;

    public int getA() {
        return a;
    }

    void setA(int a) {
        this.a = a;
    }

    protected Field clone() {
        Object object = null;
        try {
            object = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (Field)object;
    }
}

/**
 * 深度拷贝，则需要将实现了Cloneable接口并重写了clone方法的类中，
 * 所有的引用类型也全部实现Cloneable接口并重写clone方法，
 * 而且需要将引用类型的属性全部拷贝一遍。
 */
class DeepPrototype implements Cloneable {
    private int x;
    private int y;
    private int z;
    private Field field;

    public DeepPrototype() {
        this.x = 2;
        this.y = 3;
        this.z = 4;
        this.field = new Field();
        this.field.setA(5);
    }

    public Field getField() {
        return field;
    }

    protected DeepPrototype clone() {
        Object object = null;
        try {
            object = super.clone();
            ((DeepPrototype)object).field = this.field.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (DeepPrototype)object;
    }
}