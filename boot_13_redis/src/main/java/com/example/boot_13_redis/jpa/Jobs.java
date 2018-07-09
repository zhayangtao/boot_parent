package com.example.boot_13_redis.jpa;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/07
 * 定时任务
 */
@Component
public class Jobs {
    @Scheduled(fixedDelay = 5000)// 业务执行完毕后5秒钟再执行
    public void fixedDelayJob() {
        System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+" >>fixedDelay执行....");
    }

    @Scheduled(fixedRate = 5000) // 每 5 秒钟执行一次
    public void fixedRateJob() {
        System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+" >>fixedRate执行....");
    }

    @Scheduled(cron = "0 50 * * * ?")
    public void cornJob() {
        System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+" >>cron执行....");
    }
}
