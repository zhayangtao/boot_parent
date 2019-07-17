package com.example.boot_akka.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/05/22
 */
public class WriterManager {
    private static Map<String, Writer> writerMap = new HashMap<>();

    /**
     * 添加作者
     * @param writer
     */
    public void add(Writer writer) {
        writerMap.put(writer.getName(), writer);
    }

    /**
     * 根据作者名获取作者
     * @param name
     * @return
     */
    public Writer getWriter(String name) {
        return writerMap.get(name);
    }

    public WriterManager(){}

    public static WriterManager getInstance() {
        return WriterManagerInstance.instance;
    }

    private static class WriterManagerInstance {
        private static WriterManager instance = new WriterManager();
    }
}
