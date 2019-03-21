package com.example.boot.eventbus;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/09/29
 */
public class TestDeadEventListeners {

    @Test
    public void testDeadEventListeners() {
        EventBus eventBus = new EventBus("testDeadEvent");
        DeadEventListener deadEventListener = new DeadEventListener();
        eventBus.register(deadEventListener);
        eventBus.post(new TestEvent(200));
        System.out.println("deadEvent:" + deadEventListener.isNotDelivered());
    }
}
