package com.example.boot_13_redis;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/05
 * 分布式锁
 */
public interface IRedisLockHandler {
    /**
     * 获取锁 如果所可用，返回 true，否则返回 false
     * @param key
     * @return
     */
    boolean tryLock(String key);

    /**
     * 锁在给定的等待时间内空闲则返回 true，否则返回 false
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    boolean tryLock(String key, long timeout, TimeUnit unit);

    /**
     * 如果锁空闲立即返回，获取失败一直等待
     * @param key
     */
    void lock(String key);

    /**
     * 批量获取锁，如果全部获取，立即返回 true，部分获取失败，返回 false
     * @param keyList
     * @return
     */
    boolean tryLock(List<String> keyList);

    /**
     * 锁在给定的等待时间内空闲则获取成功，返回 true，否则返回 false
     * @param keyList
     * @param timeout
     * @param unit
     * @return
     */
    boolean tryLock(List<String> keyList, long timeout, TimeUnit unit);

    /**
     * 释放锁
     * @param key
     */
    void unLock(String key);

    /**
     * 批量释放锁
     * @param keyList
     */
    void unLick(List<String> keyList);
}
