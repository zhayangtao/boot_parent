package com.example.boot_webflux.functions;

import java.lang.annotation.*;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/27
 * 可重复注解
 */
public class RepeatingAnnotations {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    private @interface Filters {
        Filter[] value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class)
    private @interface Filter {
        String value();
    }

    @Filter("filter1")
    @Filter("filter2")
    private interface Filterable {
    }

    public static void main(String[] args) {
        for (Filter filter : Filterable.class.getAnnotationsByType(Filter.class)) {
            System.out.println(filter.value());
        }
    }
}
