package com.example.boot_start_learning.chapter2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/11
 */
@Getter
@Setter
@ToString
public class Singer {
    public static final String DEFAULT_NAME = "Eric";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void init() {
        System.out.println("Initializing bean");
        if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("You must set the age property of any beans");
        }
    }
}
