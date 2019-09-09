package com.example.boot_start_learning.chapter11_task;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/30
 */
public class ScheduleTaskDemo {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskConfig.class);
        CarService carService = context.getBean("carService", CarService.class);

        while (!carService.isDone()) {
            System.out.println("waiting for scheduled job to end...");
            Thread.sleep(290);
        }
        context.close();
    }
}
