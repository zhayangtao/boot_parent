package com.calprice.designmodel.strategy;

import com.calprice.designmodel.factory.CashSuper;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/11
 */
public class CashContext {
    private CashSuper cashSuper;

    public CashContext(CashSuper cashSuper) {
        this.cashSuper = cashSuper;
    }

    public double getResult(double money) {
        return cashSuper.acceptCash(money);
    }
}
