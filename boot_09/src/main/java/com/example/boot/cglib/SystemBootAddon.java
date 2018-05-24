package com.example.boot.cglib;

import org.springframework.core.Ordered;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/12
 */
public interface SystemBootAddon extends Ordered {

    void OnReady();
}
