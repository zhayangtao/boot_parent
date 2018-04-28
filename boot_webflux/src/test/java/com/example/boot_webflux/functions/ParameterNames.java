package com.example.boot_webflux.functions;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/27
 * 获取方法参数名
 */
public class ParameterNames {

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = ParameterNames.class.getMethod("main", String[].class);
        for (Parameter parameter : method.getParameters()) {
            System.out.println("Parameter: " + parameter.getName());
        }
    }
}
