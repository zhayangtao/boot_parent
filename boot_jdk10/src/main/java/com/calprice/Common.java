package com.calprice;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/30
 */
public class Common implements CalPrice {

    @Override
    public Double calPrice(Double originalPrice) {
        return originalPrice;
    }
}

@TotalValidRegion(min = 1000, max = 2000)
class Vip implements CalPrice {

    @Override
    public Double calPrice(Double originalPrice) {
        return originalPrice * 0.8;
    }
}

@TotalValidRegion(min = 2000, max = 3000)
class SuperVip implements CalPrice {

    @Override
    public Double calPrice(Double originalPrice) {
        return originalPrice * 0.7;
    }
}

@TotalValidRegion(min = 3000)
class GoldVip implements CalPrice {

    @Override
    public Double calPrice(Double originalPrice) {
        return originalPrice * 0.5;
    }
}