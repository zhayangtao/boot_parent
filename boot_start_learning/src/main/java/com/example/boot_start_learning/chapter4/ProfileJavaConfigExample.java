package com.example.boot_start_learning.chapter4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/12
 */
public class ProfileJavaConfigExample {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(KindergartenConfig.class, HighschoolConfig.class);
        FoodProviderService foodProviderService = context.getBean("foodProviderService", FoodProviderService.class);
        List<Food> lunchSet = foodProviderService.provideLunchSet();
        lunchSet.forEach(food -> System.out.println("Food: " + food.getName()));
        context.close();

    }
}
