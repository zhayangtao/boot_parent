package com.example.boot.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/11
 */
@Component
public class MailSender implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void sendMail(String to) {
        System.out.println("MailSender模拟发送邮件");
        MailSendEvent event = new MailSendEvent(this.context, to);
        // 向容器中的监听器发送事件
        context.publishEvent(event);
    }
}
