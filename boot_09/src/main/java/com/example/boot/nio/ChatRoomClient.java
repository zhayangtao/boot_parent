package com.example.boot.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/09
 */
public class ChatRoomClient {
    private Selector selector;
    private static final int PORT = 9999;
    private Charset charset = Charset.forName("UTF-8");
    private String name = "";
    private static final String USER_EXIST = "system message: user exist, please change a name";
    private static final String USER_CONTENT_SPLIT = "#@#";

    private void init() throws IOException {
        selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", PORT));
        socketChannel.configureBlocking(false); // 非阻塞模式
        socketChannel.register(selector, SelectionKey.OP_READ);

        new Thread((new ClientThread())).start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if ("".equals(line)) {
                continue;
            }
            if ("".equals(name)) {
                name = line;
                line = name + USER_CONTENT_SPLIT;
            } else {
                line = name + USER_CONTENT_SPLIT + line;
            }
            socketChannel.write(charset.encode(line));
        }
    }

    private class ClientThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    int readyChannels = selector.select();
                    if (readyChannels == 0) {
                        continue;
                    }
                    Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        iterator.remove();
                        dealWithSelectionKey(selectionKey);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void dealWithSelectionKey(SelectionKey selectionKey) throws IOException {
            if (selectionKey.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                StringBuilder content = new StringBuilder();
                while (socketChannel.read(byteBuffer) > 0) {
                    byteBuffer.flip();
                    content.append(charset.decode(byteBuffer));
                }
                if (USER_EXIST.contentEquals(content)) {
                    name = "";
                }
                System.out.println(content);
                selectionKey.interestOps(SelectionKey.OP_READ);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatRoomClient().init();
    }
}
