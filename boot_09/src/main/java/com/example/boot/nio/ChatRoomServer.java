package com.example.boot.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/09
 * 网络多客户端聊天室
 */
public class ChatRoomServer {

    private Selector selector;
    private static final int port = 9999;
    private Charset charset = Charset.forName("UTF-8");
    private static HashSet<String> users = new HashSet<>();
    private static final String USER_EXIST = "system message: user exist, please change a name";
    private static final String USER_CONTENT_SPLIT = "#@#"; // 自定义协议格式
    private static final boolean flag = false;

    private void init() throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        // 非阻塞方式
        serverSocketChannel.configureBlocking(false);
        // 注册到选择器上，设置为监听状态
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server is listening now...");
        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                dealWithSelectionKey(serverSocketChannel, selectionKey);
            }
        }

    }

    private void dealWithSelectionKey(ServerSocketChannel serverSocketChannel, SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false); // 非阻塞
            // 读取模式
            socketChannel.register(selector, SelectionKey.OP_READ);
            // 将此对应的 channel 设置为准备接受其他客户端请求
            selectionKey.interestOps(SelectionKey.OP_ACCEPT);
            System.out.println("Server is listening from client:" + socketChannel.getRemoteAddress());
            socketChannel.write(charset.encode("Please input your name."));
        }
        // 处理客户端数据读取请求
        if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            StringBuilder content = new StringBuilder();
            try {
                while (socketChannel.read(byteBuffer) > 0) {
                    byteBuffer.flip();
                    content.append(charset.decode(byteBuffer));
                }
                System.out.println("Server is listening from client " + socketChannel.getRemoteAddress() + "data rev is: " + content);
                // 将此对应的 channel 设置为准备接受下一次请求
                selectionKey.interestOps(SelectionKey.OP_READ);
            } catch (IOException e) {
                selectionKey.cancel();
                if (selectionKey.channel() != null) {
                    selectionKey.channel().close();
                }
            }
            if (content.length() > 0) {
                String[] contents = content.toString().split(USER_CONTENT_SPLIT);
                if (contents.length == 1) {
                    String name = contents[0];
                    if (users.contains(name)) {
                        socketChannel.write(charset.encode(USER_EXIST));
                    } else {
                        users.add(name);
                        String message = "welcome " + name + " to chat room! Online numbers:" + onLineNum(selector);
                        broadCast(selector, null, message);
                    }
                } else if (contents.length > 1) { // 注册完了，发送消息
                    String name = contents[0];
                    String message = content.substring(name.length()) + USER_CONTENT_SPLIT.length();
                    message = name + " say " + message;
                    if (users.contains(name)) {
                        // 不用发给发送内容的客户端
                        broadCast(selector, socketChannel, message);
                    }
                }
            }
        }
    }

    /**
     * 发送集体广播
     * @param selector
     * @param socketChannel
     * @param message
     */
    private void broadCast(Selector selector, SocketChannel socketChannel, String message) throws IOException {
        for (SelectionKey selectionKey : selector.keys()) {
            Channel channel = selectionKey.channel();
            if (channel instanceof SocketChannel && channel != socketChannel) {
                SocketChannel socketChannel1 = (SocketChannel) channel;
                socketChannel1.write(charset.encode(message));
            }
        }
    }

    /**
     * 检测下线
     * @param selector
     * @return
     */
    private long onLineNum(Selector selector) {
        return selector.keys().stream().map(SelectionKey::channel).filter(channel -> channel instanceof SocketChannel).count();
    }

    public static void main(String[] args) throws IOException {
        new ChatRoomServer().init();
    }
}
