package com.example.boot_start_learning.chapter2;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/09
 */
public interface MessageRenderer {
    void render();

    void setMessageProvider(MessageProvider provider);

    MessageProvider getMessageProvider();
}
