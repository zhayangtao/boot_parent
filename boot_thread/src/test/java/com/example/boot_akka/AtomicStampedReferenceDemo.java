package com.example.boot_akka;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/28
 */
public class AtomicStampedReferenceDemo {
    private static AtomicStampedReference<Integer> money = new AtomicStampedReference<>(19, 0);

    public static void main(String[] args) {
        // 模拟多个线程同时更新数据库，为用户充值
        for (int i = 0; i < 3; i++) {
            final int timestamp = money.getStamp();
            new Thread(() -> {
                while (true) {
                    while (true) {
                        Integer m = money.getReference();
                        if (m < 20) {
                            if (money.compareAndSet(m, m + 20, timestamp, timestamp + 1)) {
                                System.out.println("余额小于20元，充值成功，余额：" + money.getReference() + "元");
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }).start();
        }
        // 用户消费线程
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                while (true) {
                    int timestamp = money.getStamp();
                    Integer m = money.getReference();
                    if (m > 10) {
                        System.out.println("大于10元");
                        if (money.compareAndSet(m, m - 10, timestamp, timestamp + 1)) {
                            System.out.println("成功消费10元，余额：" + money.getReference());
                            break;
                        }
                    } else {
                        System.out.println("没有足够金额");
                        break;
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
