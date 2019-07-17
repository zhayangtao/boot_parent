package com.example.boot_start_learning.chapter5_spring_aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/17
 * 注解匹配切入点
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface AdviceRequired {
}

