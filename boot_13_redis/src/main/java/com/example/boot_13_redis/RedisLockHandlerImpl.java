package com.example.boot_13_redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/05
 */
public class RedisLockHandlerImpl implements IRedisLockHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLockHandlerImpl.class);

    public static final int DEFAULT_SINGLE_EXPIRE_TIME = 30;

    public static final int DEFAULT_BATCH_EXPIRE_TIME = 60;

    private final JedisPool jedisPool;

    public RedisLockHandlerImpl(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public boolean tryLock(String key) {
        return false;
    }

    @Override
    public boolean tryLock(String key, long timeout, TimeUnit unit) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            long nano = System.nanoTime();
            do {
                LOGGER.debug("try lock key: " + key);
                Long i = jedis.setnx(key, key);
                if (i == 1) {
                    jedis.expire(key, DEFAULT_SINGLE_EXPIRE_TIME);
                    LOGGER.debug("get lock, key: " + key + ", expire in " + DEFAULT_SINGLE_EXPIRE_TIME + " seconds.");
                    return true;
                } else {
                    if (LOGGER.isDebugEnabled()) {
                        String desc = jedis.get(key);
                        LOGGER.debug("key: " + key + " locked by another business：" + desc);
                    }
                }
                if (timeout <= 0) {
                    break;
                }
                TimeUnit.MILLISECONDS.sleep(300);
            } while ((System.nanoTime() - nano) < unit.toNanos(timeout));
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            returnBrokenResource(jedis);
        } finally {
            returnBrokenResource(jedis);
        }
        return false;
    }

    @Override
    public void lock(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            do {
                LOGGER.debug("try lock key: " + key);
                Long i = jedis.setnx(key, key); // set if not exists，设置成功返回 1，失败返回 0
                if (i == 1) {
                    jedis.expire(key, DEFAULT_SINGLE_EXPIRE_TIME);
                    LOGGER.debug("get lock, key: " + key + ", expire in " + DEFAULT_SINGLE_EXPIRE_TIME + " seconds.");
                    return;
                } else {
                    if (LOGGER.isDebugEnabled()) {
                        String desc = jedis.get(key);
                        LOGGER.debug("key: " + key + " locked by another business：" + desc);
                    }
                }
                TimeUnit.MILLISECONDS.sleep(300);
            } while (true);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            returnBrokenResource(jedis);
        } finally {
            returnBrokenResource(jedis);
        }
    }

    @Override
    public boolean tryLock(List<String> keyList) {
        return false;
    }

    @Override
    public boolean tryLock(List<String> keyList, long timeout, TimeUnit unit) {
        Jedis jedis = null;
        try {
            List<String> needLocking = new CopyOnWriteArrayList<>();
            List<String> locked = new CopyOnWriteArrayList<>();
            jedis = getResource();
            long nano = System.nanoTime();
            do {
                Pipeline pipeline = jedis.pipelined();
                keyList.forEach(s -> {
                    needLocking.add(s);
                    pipeline.setnx(s, s);
                });
                LOGGER.debug("try lock keys: " + needLocking);
                List<Object> results = pipeline.syncAndReturnAll();
                for (int i = 0; i < results.size(); ++i) {
                    Long result = (Long) results.get(i);
                    String key = needLocking.get(i);
                    if (result == 1) { // setnx成功，获得锁
                        jedis.expire(key, DEFAULT_BATCH_EXPIRE_TIME);
                        locked.add(key);
                    }
                }
                needLocking.removeAll(locked);
                if (needLocking.size() == 0) {
                    return true;
                } else {
                    // 部分资源未能锁住
                    LOGGER.debug("keys: " + needLocking + " locked by another business：");
                }
                if (timeout == 0) {
                    break;
                }
                TimeUnit.MILLISECONDS.sleep(500);
            } while ((System.nanoTime() - nano) < unit.toNanos(timeout));
            if (locked.size() > 0) {
                jedis.del(locked.toArray(new String[0]));
            }
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            returnBrokenResource(jedis);
        } finally {
            returnBrokenResource(jedis


            );
        }
        return false;
    }

    @Override
    public void unLock(String key) {

    }

    @Override
    public void unLick(List<String> keyList) {

    }

    /**
     * 获取 redis 客户端
     * @return
     */
    private Jedis getResource() {
        return jedisPool.getResource();
    }

    /**
     * 关闭连接
     * @param jedis
     */
    private void returnBrokenResource(Jedis jedis) {
        if (jedis == null) {
            return;
        }
        try {
            jedis.close();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
