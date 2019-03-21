package com.example.boot_15.threads;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/13
 * 测试 guava 工具类
 */
public class GuavaTest {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(0.5);
        List<Runnable> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new UserRequest(i));
        }

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (Runnable runnable : list) {
            System.out.println("等待时间：" + rateLimiter.acquire());
            executorService.execute(runnable);
        }

        executorService.shutdown();
    }

    @Test
    public void test1() {
        RateLimiter limiter = RateLimiter.create(1);
        for (int i = 1; i < 10; i += 2) {
            double waitTime = limiter.acquire(i);
            System.out.println("curTime=" + System.currentTimeMillis() + "acq:" + i + " waitTime:" + waitTime);
        }
    }

    private static class UserRequest implements Runnable {
        private int id;

        UserRequest(int i) {
            this.id = i;
        }

        @Override
        public void run() {

        }
    }
}
