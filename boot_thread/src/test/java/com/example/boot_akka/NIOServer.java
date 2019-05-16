package com.example.boot_akka;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/07
 */
public class NIOServer {
    private Selector selector;
    private ExecutorService executorService = Executors.newCachedThreadPool();
    public static Map<Socket, Long> time_stat = new HashMap<>(10240);

    private void startServer() throws IOException {
        selector = SelectorProvider.provider().openSelector();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 8000);
//        InetSocketAddress inetSocketAddress1 = new InetSocketAddress(8000);
        serverSocketChannel.socket().bind(inetSocketAddress);

        SelectionKey acceptKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        for (; ; ) {
            selector.select();
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> i = readyKeys.iterator();
            long e = 0;
            while (i.hasNext()) {
                SelectionKey selectionKey = i.next();
                i.remove();
                if (selectionKey.isAcceptable()) {
//                    doAccept(selectionKey);
                } else if (selectionKey.isValid() && selectionKey.isReadable()) {
                    if (!time_stat.containsKey(((SocketChannel) selectionKey.channel()).socket())) {
                        time_stat.put(((SocketChannel) selectionKey.channel()).socket(), System.currentTimeMillis());
//                        doRead(selectionKey);
                    }
                } else if (selectionKey.isValid() && selectionKey.isWritable()) {
//                    doWrite(selectionKey);
                }
            }
        }
    }
}
