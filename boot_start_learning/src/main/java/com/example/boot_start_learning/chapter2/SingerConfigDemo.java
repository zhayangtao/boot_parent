package com.example.boot_start_learning.chapter2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/11
 */
public class SingerConfigDemo {
    @Configuration
    static class SingerConfig {

        @Lazy
        @Bean(initMethod = "init")
        Singer singer1() {
            Singer singer = new Singer();
            singer.setName("John Mayer");
            singer.setAge(39);
            return singer;
        }

        @Lazy
        @Bean(initMethod = "init")
        Singer singer2() {
            Singer singer = new Singer();
            singer.setName("John Mayer");
            singer.setAge(39);
            return singer;
        }

        @Lazy
        @Bean(initMethod = "init")
        Singer singer3() {
            Singer singer = new Singer();
            singer.setName("John Mayer");
            singer.setAge(39);
            return singer;
        }
    }

    public static void main(String[] args) {
        GenericApplicationContext context = new AnnotationConfigApplicationContext(SingerConfig.class);
        System.out.println(context.getBean("singer1"));
        System.out.println(context.getBeansOfType(Singer.class));
    }
}
