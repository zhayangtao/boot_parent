package com.example.boot_webflux;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/09
 */
public class CustomFlux {

    @Test
    public void testGenerate() {
        final AtomicInteger count = new AtomicInteger(1);
        Flux.generate(synchronousSink -> {
            synchronousSink.next(count.get() + " : " + new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count.getAndIncrement() >= 5) {
                synchronousSink.complete();
            }
        }).subscribe(System.out::println);

    }
    // 增加一个伴随状态
    @Test
    public void testGenerate2() {
        Flux.generate(() -> 1, (integer, synchronousSink) -> {
            synchronousSink.next(integer + " : " + new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (integer >= 5) {
                synchronousSink.complete();
            }
            return integer + 1;
        }).subscribe(System.out::println);
    }

}
