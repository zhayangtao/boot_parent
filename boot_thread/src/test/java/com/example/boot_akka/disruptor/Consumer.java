package com.example.boot_akka.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/01
 */
public class Consumer implements WorkHandler<PCData> {
    @Override
    public void onEvent(PCData pcData) throws Exception {
        System.out.println(Thread.currentThread().getId() + ":Event: --"
                + pcData.getValue() * pcData.getValue() + "--");
    }
}
