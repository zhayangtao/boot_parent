package com.example.jfinaldemo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/23
 */
public class CglibInfoProxyTest implements MethodInterceptor{

    private Object object;

    public Object newInstance(Object source) {
        object = source;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.object.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before method!!!");
        Object value = methodProxy.invoke(o, objects);
        return value;
    }

    public static void main(String[] args) {
        InfoDemo infoDemo = (InfoDemo) new CglibInfoProxyTest().newInstance(new InfoDemo());
        infoDemo.welcome("zhangsan");
    }
}

class InfoDemo {
    public void welcome(String person) {
        System.out.println("welcome : " + person);
    }
}