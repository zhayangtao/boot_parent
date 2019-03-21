package com.example.boot_15.threads;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/13
 */
//@Aspect
//@Component
class EmployeeAdvice {

    @Before("execution(* com.example.boot_15.threads.*Service.*(..))")
    public void advisor() {
        System.out.println("do before");
    }
}
