package com.example.boot_start_learning.chapter5_spring_aop;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/17
 */
class AdviceGuitarist implements Singer {

    @Override
    public void sing() {
        System.out.println("Dream of ways to throw it all away");
    }

    @AdviceRequired
    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    void rest() {
        System.out.println("zzz");
    }
}
