package com.example.boot_webflux.functions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/27
 * 1.8 中的 扩展注解
 */
public class ExtendedAnnotation {

    @Target(ElementType.TYPE_USE) // TYPE_USE 表示任何类型
    @Retention(RetentionPolicy.RUNTIME)
    @interface NonEmpty {

    }

    public static class Holder<@NonEmpty T> extends @NonEmpty Object {
        public void method() throws @NonEmpty Exception {

        }
    }

    public static void main(String[] args) {
        final Holder<String> holder = new @NonEmpty Holder<>();
        @NonEmpty Collection<@NonEmpty String> strings = new ArrayList<>();
    }
}
