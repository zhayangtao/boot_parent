package com.example.boot.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2017/12/29
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    @Cacheable(value = "helloCache") // Cacheable 表明方法支持缓存
    public String hello(String name) {
        System.out.println("没有走缓存");
        return "hello " + name;
    }

    /**
     * 一个支持缓存的方法在对象内部被调用时不会触发缓存功能
     *
     * @param name
     * @return
     */
    @RequestMapping("/condition")
    @Cacheable(value = "condition", condition = "#name.length() <= 4")
    public String condition(String name) {
        System.out.println("没有走缓存");
        return "hello " + name;
    }

    @RequestMapping("/cachePut")
    @CachePut(value = "cachePut", key = "#name")
    public String cachePut(String name) {
        System.out.println("执行了数据库操作");
        return "hello " + name;
    }

    @RequestMapping("/getUser")
    @Cacheable(value = "userCache", key = "1000")
    public String getUserByName(String userName) {
        System.out.println("两次调用第一次会调用，第二次不会调用");
        return "hello " + userName;
    }

    @RequestMapping("/updateUser")
    @CachePut(value = "userCache", key = "1000")
    public String updateUser(String userName) {
        userName += "hiahiahia";
        System.out.println("调用更新updateUser");
        return "update " + userName;
    }

    /**
     * CacheEvict用于清理缓存
     *
     * @param name
     * @return
     */
    @RequestMapping("/allEntries")
    @CacheEvict(value = "userCache", allEntries = true)
    public String allEntries(String name) {
        System.out.println("执行了数据库操作");
        return "hello " + name;
    }

    /**
     * 清除操作默认是在对应方法成功执行之后触发的，
     * 即方法如果因为抛出异常而未能成功返回时也不会触发清除操作。
     * 使用 beforeInvocation 可以改变触发清除操作的时间，
     * 当我们指定该属性值为 true 时，Spring 会在调用该方法之前清除缓存中的指定元素。
     */
    @RequestMapping("/beforeInvocation")
    @CacheEvict(value = "userCache", allEntries = true, beforeInvocation = true)
    public void beforeInvocation() {
        throw new RuntimeException("test beforeInvocation");
    }
}
