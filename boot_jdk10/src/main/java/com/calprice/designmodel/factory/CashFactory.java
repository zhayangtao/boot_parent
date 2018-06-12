package com.calprice.designmodel.factory;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/11
 * 现金收费工厂类
 */
public class CashFactory {

    public static CashSuper createCashAccept(String type) {
        CashSuper cashSuper = null;
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
        return cashSuper;
    }
}
