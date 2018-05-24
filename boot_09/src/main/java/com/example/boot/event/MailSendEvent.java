package com.example.boot.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/11
 */
public class MailSendEvent extends ApplicationContextEvent{

    private String to;

    String getTo() {
        return to;
    }

    MailSendEvent(ApplicationContext source, String to) {
        super(source);
        this.to = to;
    }

    /**
     * Create a new ContextStartedEvent.
     *
     * @param source the {@code ApplicationContext} that the event is raised for
     *               (must not be {@code null})
     */
    public MailSendEvent(ApplicationContext source) {
        super(source);
    }

}
