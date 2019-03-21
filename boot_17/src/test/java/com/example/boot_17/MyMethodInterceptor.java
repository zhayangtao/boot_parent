package com.example.boot_17;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/20
 * cglib 动态代理
 */
public class MyMethodInterceptor implements MethodInterceptor {
    /**
     * o：cglib生成的代理对象
     * method：被代理对象方法
     * objects：方法入参
     * methodProxy: 代理方法
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("======插入前置通知======");
        Object o1 = methodProxy.invokeSuper(o, objects);
        System.out.println("======插入后者通知======");
        return o1;
    }

    public static void main(String[] args) throws InterruptedException {
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(CyclicBarrierTest.class);
        // 设置enhancer的回调对象
        enhancer.setCallback(new MyMethodInterceptor());
        // 创建代理对象
        CyclicBarrierTest cyclicBarrierTest = (CyclicBarrierTest) enhancer.create();
        cyclicBarrierTest.test1();
    }
}
