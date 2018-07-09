package com.example.boot_13_redis.jpa;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/07
 */
@Entity
@Data
public class Order {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Column
    private Integer orderId;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column
    private OrderStatus status;
}
