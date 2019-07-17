package com.example.boot_akka.design;

import java.util.EventObject;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/05/22
 */
public class WriterEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public WriterEvent(Object source) {
        super(source);
    }

    public Writer getWriter() {
        return (Writer) super.getSource();
    }

}
