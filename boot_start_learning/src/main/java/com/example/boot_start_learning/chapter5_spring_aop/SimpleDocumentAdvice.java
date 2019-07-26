package com.example.boot_start_learning.chapter5_spring_aop;

import org.aopalliance.intercept.Joinpoint;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/18
 */
public class SimpleDocumentAdvice {
    public void simpleBeforeAdvice(Joinpoint joinpoint) {
        System.out.println("Executing: " + joinpoint.getClass().getName());
    }
}
