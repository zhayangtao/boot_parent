package com.example.boot_start_learning.chapter5_spring_aop;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 */
public class Guitarist {
    private String lyric = "you're gonna live forever in me";

    public void sing() {
        System.out.println(lyric);
    }

    public void sing2() {
        System.out.println("Just keep me sing2");
    }

    public void rest() {
        System.out.println("zzz");
    }
}
