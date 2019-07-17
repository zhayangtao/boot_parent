package com.example.boot_start_learning.chapter5_spring_aop;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 */
public class SampleBean {
    public void foo(int x) {
        System.out.println("Invoke foo() with: " + x);
    }

    public void bar() {
        System.out.println("invoked bar()");
    }
}
