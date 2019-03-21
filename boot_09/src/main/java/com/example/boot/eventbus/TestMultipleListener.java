package com.example.boot.eventbus;

import com.google.common.eventbus.EventBus;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/09/28
 */
public class TestMultipleListener {
    @Test
    public void testMultipleEvents() {
        EventBus eventBus = new EventBus("testMultiple");
        MultipleListener multipleListener = new MultipleListener();

        eventBus.register(multipleListener);

        eventBus.post(new Integer(100));
        eventBus.post(new Integer(200));
        eventBus.post(new Integer(300));

        eventBus.post(new Long(800));
        eventBus.post(new Long(900));
        eventBus.post(new Long(1000));
        eventBus.post(new Long(1100));

        System.out.println("lastInteger:" + multipleListener.getLastInteger());
        System.out.println("lastLong:" + multipleListener.getLastLong());


        String[] strings = StringUtils.split("aaaa#sss# ", "#", 2);
        System.out.println();

        String[] strings1 = "aaaa#sss# ".split("#");

        System.out.println();
    }
}
