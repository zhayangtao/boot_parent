package com.example.boot_start_learning.chapter5_spring_aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 */
public class SecurityAdvice implements MethodBeforeAdvice {
    private SecurityManager securityManager;

    SecurityAdvice() {
        this.securityManager = new SecurityManager();
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        UserInfo userInfo = securityManager.getLoggerOnUser();
        if (userInfo == null) {
            System.out.println("No user authenticated");
            throw new SecurityException("You must login before attempting to invoke the method:" + method.getName());
        } else if ("John".equals(userInfo.getUserName())) {
            System.out.println("Logged in user John-OKAY");
        } else {
            System.out.println("User " + userInfo.getUserName() + " is not allowed access to method " + method.getName());
        }
    }
}
