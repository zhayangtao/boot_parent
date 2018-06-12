package com.calprice.designmodel.factory;

import com.jfinal.template.stat.ast.If;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/11
 */
public class LeiFeng {
    public void sweep() {
        System.out.println("扫扫地");
    }
    public void wash() {
        System.out.println("洗衣服");
    }
    public void buyRice() {
        System.out.println("买米");
    }
}

class UnderGraduate extends LeiFeng {

}

class Volunteer extends LeiFeng {

}

// 简单工厂
class SimpleFactory {
    public static LeiFeng createLeiFeng(String type) {
        LeiFeng result = null;
        switch (type) {
            case "大学生":
                result = new UnderGraduate();
                break;
            case "志愿者":
                result = new Volunteer();
                break;
        }
        return result;
    }
}

// 工厂
interface IFactory {
    LeiFeng createLeiFeng();
}

class UndergraduateFactory implements IFactory {

    @Override
    public LeiFeng createLeiFeng() {
        return new UnderGraduate();
    }
}

class VolunteerFactory implements IFactory {

    @Override
    public LeiFeng createLeiFeng() {
        return new Volunteer();
    }
}