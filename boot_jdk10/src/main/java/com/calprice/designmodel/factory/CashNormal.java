package com.calprice.designmodel.factory;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/11
 */
public class CashNormal extends CashSuper {
    @Override
    public double acceptCash(double money) {
        return money;
    }
}
