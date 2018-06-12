package com.calprice.designmodel.strategy;

import com.calprice.designmodel.factory.CashNormal;
import com.calprice.designmodel.factory.CashRebate;
import com.calprice.designmodel.factory.CashReturn;
import com.calprice.designmodel.factory.CashSuper;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/11
 */
public class CashContextBetter {

    private CashSuper cashSuper = null;

    public CashContextBetter(String type) {
        switch (type) {
            case "正常收费":
                cashSuper = new CashNormal();
                break;
            case "满300返100":
                cashSuper = new CashReturn("300", "100");
                break;
            case "打8折":
                cashSuper = new CashRebate(0.8);
                break;
        }
    }

    public double getResult(double money) {
        return cashSuper.acceptCash(money);
    }
}
