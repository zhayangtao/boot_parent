package com.example.boot_13_redis.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/07
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByOrderId(Integer id);
}
