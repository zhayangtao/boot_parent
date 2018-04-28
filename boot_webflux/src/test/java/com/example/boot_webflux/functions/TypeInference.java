package com.example.boot_webflux.functions;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/27
 * 类型推断
 */
public class TypeInference<T> {

    private static<T> T defaultValue() {
        return null;
    }

    private T getOrDefault(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    public static void main(String[] args) {
        final TypeInference<String> inference = new TypeInference<>();
        String result = inference.getOrDefault("22", TypeInference.defaultValue());
    }
}
