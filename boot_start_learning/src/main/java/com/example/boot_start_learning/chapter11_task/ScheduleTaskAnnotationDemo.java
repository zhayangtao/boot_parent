package com.example.boot_start_learning.chapter11_task;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/30
 */
public class ScheduleTaskAnnotationDemo {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskConfig.class);

        System.in.read();
        context.close();
    }
}
