package com.example.boot_rabbitmq.redis;

import com.google.common.collect.ImmutableMap;
import redis.clients.jedis.Jedis;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/04
 */
public class JedisTest {
    private static final String HASH_KEY = "key";

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 存储 string key-value
        jedis.set(HASH_KEY, "value");
        // 如果已经存在 key，先删除
        if (jedis.exists(HASH_KEY)) {
            System.out.println(jedis.get(HASH_KEY));
            jedis.del(HASH_KEY);
        }
        // 存入单个 key-value
        jedis.hset(HASH_KEY, "username", "yourUsername");
        // 存入多个 key-value
        ImmutableMap<String, String> map = ImmutableMap.of("password", "yourPassword", "age", "20");
        jedis.hmset(HASH_KEY, map);
        // 判断某个 key 是否在指定的 hash key 中
        boolean exits = jedis.hexists(HASH_KEY, "username");
        System.out.println(exits);
        //Set<String > keys = jedis.keys("*");
        //keys.forEach(jedis::del);
        // 获取 某个 hash 中键值对的数量
        Long len = jedis.hlen(HASH_KEY);
        System.out.println(len);
        // 获取一个 hash 中所有的 key
        System.out.println(jedis.hkeys(HASH_KEY));
        // 获取一个 hash 中 所以 values
        System.out.println(jedis.hvals(HASH_KEY));

        System.out.println(jedis.hgetAll(HASH_KEY));
        System.out.println(jedis.hmget(HASH_KEY, "username", "password"));
    }
}
