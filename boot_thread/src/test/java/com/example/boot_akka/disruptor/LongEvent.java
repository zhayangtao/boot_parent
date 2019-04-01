package com.example.boot_akka.disruptor;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/29
 * 使用 Disruptor
 */
public class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }
}
