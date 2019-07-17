package com.example.boot_start_learning.chapter4;

import com.example.boot_start_learning.chapter2.MessageProvider;
import com.example.boot_start_learning.chapter2.MessageRenderer;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/12
 */
public class StandardOutMessageRenderer implements MessageRenderer {
    private MessageProvider messageProvider;

    public StandardOutMessageRenderer() {
        System.out.println("--> StandardOutMessageRenderer: constructor called");
    }

    @Override
    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException("you must set messageProvider");
        }
    }

    @Override
    public void setMessageProvider(MessageProvider provider) {
        System.out.println("--> StandardOutMessageRenderer: setting the provider");
        this.messageProvider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }
}
