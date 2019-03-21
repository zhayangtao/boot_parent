package com.example.boot.eventbus;

import com.google.common.eventbus.Subscribe;
import lombok.Getter;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/09/28
 */
@Getter
public class MultipleListener {
    public Integer lastInteger;
    public Long lastLong;

    @Subscribe
    public void listenInteger(Integer event) {
        lastInteger = event;
        System.out.println("event Integer:" + lastInteger);
    }

    @Subscribe
    public void listenLong(Long event) {
        lastLong = event;
        System.out.println("event Long:" + lastLong);
    }

}
