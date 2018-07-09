package com.example.boot_13_redis.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/06
 */
@Entity
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;


}
