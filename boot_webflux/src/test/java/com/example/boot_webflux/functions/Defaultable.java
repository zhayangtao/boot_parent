package com.example.boot_webflux.functions;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/27
 */
public interface Defaultable {

    default String noRequired() {
        return "Default implementation";
    }
}

class DefaultableImpl implements Defaultable {
}

class OverrideAbleImpl implements Defaultable {
    @Override
    public String noRequired() {
        return "Overridden implementation";
    }
}