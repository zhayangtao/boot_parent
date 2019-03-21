package com.example.boot.eventbus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/09/29
 * Dead Event：EventBus 发送的消息，订阅者不关心
 */
public class DeadEventListener {
    boolean notDelivered = false;

    @Subscribe
    public void listen(DeadEvent event) {
        notDelivered = true;
    }

    public boolean isNotDelivered() {
        return notDelivered;
    }

}
