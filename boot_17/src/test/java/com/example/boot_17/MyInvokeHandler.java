package com.example.boot_17;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/20
 */
public class MyInvokeHandler<T> implements InvocationHandler {
    private T target;

    public MyInvokeHandler(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理执行" +method.getName() + "方法");
        Object result = method.invoke(target, args);
        System.out.println("代理执行" +method.getName() + "方法结束");
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrierTest test = new CyclicBarrierTest();
        MyInvokeHandler<CyclicBarrierTest> myInvokeHandler = new MyInvokeHandler<>(test);
        CyclicBarrierTest pxoxy = (CyclicBarrierTest) Proxy.newProxyInstance(CyclicBarrierTest.class.getClassLoader(), new Class[]{CyclicBarrierTest.class}, myInvokeHandler);
        pxoxy.test();
    }
}
