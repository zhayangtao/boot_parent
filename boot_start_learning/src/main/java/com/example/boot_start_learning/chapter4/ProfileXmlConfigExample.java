package com.example.boot_start_learning.chapter4;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/12
 */
public class ProfileXmlConfigExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.getEnvironment().setActiveProfiles("highschool");
        context.load("*-config.xml");
        context.refresh();
        FoodProviderService foodProviderService = context.getBean("foodProviderService", FoodProviderService.class);
        List<Food> lunchSet = foodProviderService.provideLunchSet();

        lunchSet.forEach(food -> System.out.println("Food: " + food.getName()));
        context.close();
    }
}
