package com.example.boot_start_learning.chapter4;

import com.example.boot_start_learning.chapter2.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/12
 */
public class JavaConfigExampleOne {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MessageRenderer renderer = context.getBean("messageRenderer", MessageRenderer.class);
//        renderer.render();
    }
}
