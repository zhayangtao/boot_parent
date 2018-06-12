package com.calprice.designmodel.factory;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/11
 * 返利收费子类
 */
public class CashReturn extends CashSuper {

    private double moneyCondition = 0.0d;
    private double moneyReturn = 0.0d;

    public CashReturn(String moneyCondition, String moneyReturn) {
        this.moneyCondition = Double.parseDouble(moneyCondition);
        this.moneyReturn = Double.parseDouble(moneyReturn);
    }

    @Override
    public double acceptCash(double money) {
        double result = money;
        if (money >= moneyCondition) {
            result = money - Math.floor(money / moneyCondition) * moneyReturn;
        }
        return result;
    }
}
