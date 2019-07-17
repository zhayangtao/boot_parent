package com.example.boot_start_learning.chapter5_spring_aop;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 */
public class GoodGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("Who says i can't be free");
    }
}

class GreatGuitarist implements Singer {

    @Override
    public void sing() {
        System.out.println("I shot the sheriff, But i did not shoot the deputy");
    }
}
