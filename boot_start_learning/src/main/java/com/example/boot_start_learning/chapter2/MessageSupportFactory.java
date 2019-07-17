package com.example.boot_start_learning.chapter2;

import java.io.IOException;
import java.util.Properties;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/09
 */
public class MessageSupportFactory {
    private static MessageSupportFactory instance;
    private MessageRenderer renderer;
    private MessageProvider provider;

    private MessageSupportFactory() {
        Properties properties = new Properties();

        try {
            properties.load(this.getClass().getResourceAsStream("/msf.properties"));
            String rendererClass = properties.getProperty("renderer.class");
            String providerClass = properties.getProperty("provider.class");

            renderer = (MessageRenderer) Class.forName(rendererClass).newInstance();
            provider = (MessageProvider) Class.forName(providerClass).newInstance();

            System.out.println(rendererClass);
            System.out.println(providerClass);
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    static {
        instance = new MessageSupportFactory();
    }

    public static MessageSupportFactory getInstance() {
        return instance;
    }

    public MessageRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(MessageRenderer renderer) {
        this.renderer = renderer;
    }

    public MessageProvider getProvider() {
        return provider;
    }

    public void setProvider(MessageProvider provider) {
        this.provider = provider;
    }

    public static void main(String[] args) {
        MessageRenderer renderer = MessageSupportFactory.getInstance().getRenderer();
        MessageProvider provider = MessageSupportFactory.getInstance().getProvider();

        renderer.setMessageProvider(provider);
        renderer.render();
    }
}
