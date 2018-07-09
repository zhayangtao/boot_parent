package com.example.boot_13_redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/05
 */
public class JedisTest {
    private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    static {
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(2);
    }

    private static JedisPool pool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 500);

    public static void main(String[] args) {
        Jedis jedis = pool.getResource();
        try {
            JedisPubSubListener listener = new JedisPubSubListener();
            jedis.subscribe(listener, "test_channel");
        } finally {
            pool.close();
        }
        pool.destroy();
    }
}
