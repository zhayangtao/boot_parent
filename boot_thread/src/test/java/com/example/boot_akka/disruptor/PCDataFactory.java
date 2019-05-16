package com.example.boot_akka.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/01
 */
public class PCDataFactory implements EventFactory<PCData> {
    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
