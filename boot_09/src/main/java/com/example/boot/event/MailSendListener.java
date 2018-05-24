package com.example.boot.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/11
 */
@Component
public class MailSendListener implements ApplicationListener<MailSendEvent> {
    @Override
    public void onApplicationEvent(MailSendEvent event) {
        System.out.println("MailSendListener:向" + event.getTo() + "发送了邮件");
    }
}
