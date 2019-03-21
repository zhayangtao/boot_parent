package com.example.boot_17.controller.akka;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/21
 */
public class HelloMessage {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HelloMessage(String name) {
        this.name = name;
    }
}
