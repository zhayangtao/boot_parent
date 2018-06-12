package com.calprice;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/30
 */
public class Customer {
    private Double totalAmount = 0D;
    private Double amount = 0D;
    private CalPrice calPrice = new Common();

    public void buy(Double amount) {
        this.amount = amount;
        totalAmount += amount;
        calPrice = CalPriceFactory.getInstance().createCalPrice(this);
    }

    public Double calLastAmount() {
        return calPrice.calPrice(amount);
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Double getAmount() {
        return amount;
    }
}
