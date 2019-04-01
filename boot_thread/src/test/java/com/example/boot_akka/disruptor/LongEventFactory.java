package com.example.boot_akka.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/29
 * 定义事件工厂
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
