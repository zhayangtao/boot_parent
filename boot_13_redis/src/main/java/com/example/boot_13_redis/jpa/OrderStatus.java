package com.example.boot_13_redis.jpa;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/07
 */
public enum OrderStatus {
    WAIT_PAYMENT, WAIT_DELIVER, WAIT_RECEIVE, FINISH;
}
