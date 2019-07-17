package com.example.boot_akka.design;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/05/22
 */
public class Writer {
    private String name;
    private String lastNovel;

    private Set<WriterListener> writerListenerSet = new HashSet<>();//作者类要包含一个自己监听器的列表

    public Writer(String name) {
        this.name = name;
    }

    public void addNovel(String novel) {
        System.out.println(name + "发布了新书《" + novel + "》");
        lastNovel = novel;
        fireEvent();
    }

    /**
     * 触发发布新书的事件，通知所有监听这件事的监听器
     */
    private void fireEvent() {
        WriterEvent writerEvent = new WriterEvent(this);
        for (WriterListener writerListener : writerListenerSet) {
            writerListener.addNovel(writerEvent);
        }
    }

    /**
     * 提供给外部注册成为自己的监听器的方法
     * @param writerListener
     */
    public void registerListener(WriterListener writerListener) {
        writerListenerSet.add(writerListener);
    }

    public void unregisterListener(WriterListener writerListener) {
        writerListenerSet.remove(writerListener);
    }

    public String getName() {
        return name;
    }

    public String getLastNovel() {
        return lastNovel;
    }

    public static void main(String[] args) {
        WriterManager writerManager = new WriterManager();
        Writer writer = new Writer("大刘");
        writerManager.add(writer);
        Reader reader1 = new Reader("读者1");
        Reader reader2 = new Reader("读者2");
        Reader reader3 = new Reader("读者3");
        Reader reader4 = new Reader("读者4");
        reader1.subscribe(writer.getName());
        reader2.subscribe(writer.getName());
        reader3.subscribe(writer.getName());
        reader4.subscribe(writer.getName());
        reader4.unsubscribe(writer.getName());

        writer.addNovel("三体");
    }
}
