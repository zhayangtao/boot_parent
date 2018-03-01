package com.example.jfinaldemo.controller;

import com.example.jfinaldemo.service.OrderService;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.tx.Tx;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/23
 */
public class OrderController extends Controller {

    public void payment() {
        //业务层拦截器的触发需要使用enhance加强
        OrderService service = enhance(OrderService.class);
        // 调用payment方法会触发拦截器
        service.payment(getParaToInt("oderId"), getParaToInt("userId"));
    }

    public void injectDemo() {
        // 为enhance方法传入的烂机器称为inject拦截器，将拦截被增强目标中的所有方法，执行次序在 Class 级拦截器之前
        OrderService service = enhance(OrderService.class, Tx.class);
    }
}
