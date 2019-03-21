package com.example.boot.eventbus;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/09/28
 */
public class TestEventBus {

    @Test
    public void testReceiveEvent() {
        EventBus eventBus = new EventBus("test");
        EventListener eventListener = new EventListener();
        eventBus.register(eventListener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

        System.out.println("LastMessage:" + eventListener.getLastMessage());
    }
}
