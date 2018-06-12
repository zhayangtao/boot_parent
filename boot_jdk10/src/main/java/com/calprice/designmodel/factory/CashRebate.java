package com.calprice.designmodel.factory;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/11
 * 打折收费子类
 */
public class CashRebate extends CashSuper {

    private double moneyRebate = 1d;

    public CashRebate(double moneyRebate) {
        this.moneyRebate = moneyRebate;
    }

    @Override
    public double acceptCash(double money) {
        return money * moneyRebate;
    }
}
