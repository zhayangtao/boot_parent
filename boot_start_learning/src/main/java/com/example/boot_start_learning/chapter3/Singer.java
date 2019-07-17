package com.example.boot_start_learning.chapter3;

import com.example.boot_start_learning.BootStartLearningApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/09
 */
@Service("singer")
public class Singer {

    @Autowired
    private Inspiration inspiration;

    public void sing() {
        System.out.println("... " + inspiration.getLyric());
    }

    @Bean
    public String getString1() {
        return "1";
    }

    @Bean
    public String getString2() {
        return "2";
    }

    @Bean
    public String getString3() {
        return "3";
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BootStartLearningApplication.class);
        Map<String, String> beans = context.getBeansOfType(String.class);
        beans.forEach((s, s2) -> System.out.println(s));
    }
}
