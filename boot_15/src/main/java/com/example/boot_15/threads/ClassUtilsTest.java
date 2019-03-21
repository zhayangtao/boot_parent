package com.example.boot_15.threads;

import org.junit.Test;
import org.springframework.util.ClassUtils;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/13
 */
public class ClassUtilsTest {

    @Test
    public void test1() {
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(this.getClass().getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
    }

    @Test
    public void test2() throws ClassNotFoundException {
        System.out.println(ClassUtils.forName("int", ClassUtils.getDefaultClassLoader()));
//        Class.forName("int");
    }
}
