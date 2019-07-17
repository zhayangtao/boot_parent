package com.example.boot_akka.design;

import java.util.EventListener;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/05/22
 */
public interface WriterListener /*extends EventListener */{
    void addNovel(WriterEvent writerEvent);
}
