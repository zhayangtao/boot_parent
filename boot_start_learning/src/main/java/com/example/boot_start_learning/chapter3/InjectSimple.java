package com.example.boot_start_learning.chapter3;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/09
 */
@Getter
@Setter
@ToString
public class InjectSimple {
    @Value("John Mayer")
    private String name;
    @Value("39")
    private int age;
    private float height;
    private boolean programmer;
    private Long ageInSeconds;

}
