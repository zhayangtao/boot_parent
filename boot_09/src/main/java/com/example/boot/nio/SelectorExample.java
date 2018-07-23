package com.example.boot.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/09
 */
public class SelectorExample {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        System.out.println("Selector is open for making connection: " + selector.isOpen());
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("lcoalhost", 9999));
        serverSocketChannel.configureBlocking(false); // 非阻塞模式
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        for (; ; ) {
            System.out.println("waiting for the select operation...");
            int noOfKeys = selector.select();
            System.out.println(noOfKeys);
        }

    }
}
