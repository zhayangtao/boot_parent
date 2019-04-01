package com.example.boot_akka.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/29
 * 定义时间处理的具体实现
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long l, boolean b) throws Exception {
        System.out.println("Event:" + event);
    }
}
