package com.example.boot_start_learning.chapter4;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/12
 * 应用程序事件
 */
@Getter
public class MessageEvent extends ApplicationEvent {
    private static final long serialVersionUID = -8093108561132515373L;
    private String msg;

    public MessageEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }
}
