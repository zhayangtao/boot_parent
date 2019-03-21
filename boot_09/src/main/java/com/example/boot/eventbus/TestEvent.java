package com.example.boot.eventbus;

import lombok.Getter;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/09/28
 * 测试 guava eventbus
 */
@Getter
public class TestEvent {
    private final int message;

    public TestEvent(int message) {
        this.message = message;
        System.out.println("event message:" + message);
    }
}
