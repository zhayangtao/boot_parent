package com.example.boot_start_learning.chapter5_spring_aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/19
 */
@Component
@Aspect
public class AnnotatedAdvice {

    /*@Pointcut("execution(* com.example.boot_start_learning.chapter5_spring_aop.GrammyGuitarist.*sing(Guitar))")
    public void singExecution(Guitar value) {

    }*/

    @Pointcut("bean(john*)")
    public void isJohn() {

    }
}
