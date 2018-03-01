package com.example.boot.configuration;

import com.example.boot.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2017/12/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString() {
        redisTemplate.opsForValue().set("lili", "youknownothing");
        Assert.assertEquals("youknownothing", redisTemplate.opsForValue().get("lili"));
        redisTemplate.delete("lili");
    }

    @Test
    public void testObj() {
        User user = new User("youknownothing", "smile", "youknow", "know", "3030");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("com.example", user);
        User u = operations.get("com.example");
        System.out.println("user: " + u.toString());
        redisTemplate.delete("com.example");
    }

    @Test
    public void testExpire() throws InterruptedException {
        User user = new User("youknownothing", "smile", "youknow", "know", "3030");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("expire", user, 100, TimeUnit.MILLISECONDS);
        Thread.sleep(1000);
        boolean exists = redisTemplate.hasKey("expire");
        Optional.of(redisTemplate.hasKey("expire")).ifPresent(System.out::println);
    }

    @Test
    public void testDelete() {
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        redisTemplate.opsForValue().set("deleteKey", "youKnowNothing");
        redisTemplate.delete("deleteKey");
        Optional.ofNullable(redisTemplate.hasKey("deleteKey")).ifPresent(System.out::println);
    }

    @Test
    public void testHash() {
        HashOperations operations = redisTemplate.opsForHash();
        // hash set 的时候需要传入三个参数，第一个为 key，第二个为 field，第三个为存储的值。
        // 一般情况下 Key 代表一组数据，field 为 key 相关的属性，而 value 就是属性对应的值。
        operations.put("hash", "you", "you");
        String value = (String) operations.get("hash", "you");
        System.out.println("hash value : " + value);
    }

    @Test
    public void testList() {
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        listOperations.leftPush("list", "you");
        listOperations.leftPush("list", "know");
        listOperations.leftPush("list", "nothing");
        String value = listOperations.leftPop("list");
        System.out.println(value);

        List<String> values = listOperations.range("list", 0, 2);
        values.forEach(System.out::println);
    }

    @Test
    public void testSet() {
        String key = "set";
        SetOperations<String, String> operations = redisTemplate.opsForSet();
        operations.add(key, "you");
        operations.add(key, "know");
        operations.add(key, "nothing");
        operations.add(key, "Jon Snow");
        operations.add(key, "Jon Snow");
        Set<String> values = operations.members(key);
        values.forEach(s -> System.out.println("set：" + s));
    }

    @Test
    public void testZSet() {
        String key = "zset";
        redisTemplate.delete(key);
        ZSetOperations<String, String> operations = redisTemplate.opsForZSet();
        operations.add(key, "you", 1);
        operations.add(key, "know", 2);
        operations.add(key, "nothing", 3);
        operations.add(key, "Jon snow", 4);
        Set<String> sets = operations.range(key, 0, 3);
        sets.forEach(s -> System.out.println("zset value : " + s));
        operations.rangeByScore(key, 0, 3).forEach(s -> System.out.println("zsetB value : " + s));
    }
}