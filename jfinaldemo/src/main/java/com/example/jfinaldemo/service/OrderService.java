package com.example.jfinaldemo.service;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/23
 */
public class OrderService {

    @Before(Tx.class)
    public void payment(int orderId, int userId) {
        // TODO
    }
}
