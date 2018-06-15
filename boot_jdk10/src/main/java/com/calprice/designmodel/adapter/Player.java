package com.calprice.designmodel.adapter;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/14
 * 适配器模式
 */
public abstract class Player {
    String name;

    Player(String name) {
        this.name = name;
    }

    abstract void attack();

    abstract void defense();
}

class Forwards extends Player {

    Forwards(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.printf("前锋%s进攻\n", name);
    }

    @Override
    void defense() {
        System.out.printf("前锋%s防守\n", name);
    }
}

class Center extends Player {
    Center(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.printf("中锋%s进攻\n", name);
    }

    @Override
    void defense() {
        System.out.printf("中锋%s防守\n", name);
    }
}

class Guards extends Player {
    Guards(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.printf("后卫%s进攻\n", name);
    }

    @Override
    void defense() {
        System.out.printf("后卫%s防守\n", name);
    }
}

class TestClass2 {
    public static void main(String[] args) {
        Player b = new Forwards("battry");
        b.attack();
        Player m = new Guards("kobi");
        m.attack();
        Player ym = new Center("yaoming");
        ym.attack();
        ym.defense();
    }
}

//
class ForeignCenter {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void attack_chinese() {
        System.out.printf("外籍中锋%s进攻", name);
    }

    void defense_chinese() {
        System.out.printf("外籍中锋%s防守", name);
    }
}

// 适配器
class Translator extends Player {

    private ForeignCenter center = new ForeignCenter();

    public Translator(String name) {
        super(name);
    }

    @Override
    void attack() {
        center.attack_chinese();
    }

    @Override
    void defense() {
        center.defense_chinese();
    }
}