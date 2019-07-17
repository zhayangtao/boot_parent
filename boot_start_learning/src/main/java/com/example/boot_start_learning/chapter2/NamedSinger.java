package com.example.boot_start_learning.chapter2;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/11
 */
@Configuration
public class NamedSinger implements BeanNameAware {
    private String name;

    @Override
    public void setBeanName(String s) {
        this.name = s;
    }

    public void sing() {
        System.out.println("singer " + name + " - sing()");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(NamedSinger.class);
        NamedSinger namedSinger = context.getBean(NamedSinger.class);
        namedSinger.sing();
        context.close();
    }
}
