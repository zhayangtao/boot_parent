package com.example.boot_start_learning.chapter5_spring_aop;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/17
 */
public class GrammyGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("Sing: Gravity is working against me");
    }

    public void sing(Guitar guitar) {
        System.out.println("Play: " + guitar.play());
    }

    public void rest() {
        System.out.println("zzz");
    }

    public void talk() {
        System.out.println("talk");
    }
}

class Guitar {
    String play() {
        return "G C G C Am D7";
    }
}