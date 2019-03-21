package com.example.boot.eventbus;

import com.google.common.eventbus.Subscribe;
import lombok.Getter;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/09/28
 */
@Getter
public class EventListener {
    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message:" + lastMessage);
    }

}
