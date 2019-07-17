package com.example.boot_start_learning.chapter4;

import org.springframework.context.ApplicationListener;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/12
 */
public class MessageEventListener implements ApplicationListener<MessageEvent> {
    @Override
    public void onApplicationEvent(MessageEvent messageEvent) {
        System.out.println("received: " + messageEvent.getMsg());
    }
}
