package com.example.boot_start_learning.chapter2;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/09
 */
public class MessageRendererImpl implements MessageRenderer {
    private MessageProvider messageProvider;

    @Override
    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException("You must set the property messageProvider of class");
        }
    }

    @Override
    public void setMessageProvider(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }

    public static void main(String[] args) {
        MessageRenderer renderer = new MessageRendererImpl();
        MessageProvider provider = new MessageProviderImpl();
        renderer.setMessageProvider(provider);
        renderer.render();
    }
}
