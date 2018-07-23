package com.example.boot.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Date;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/10
 */
public class TimeServer {
    private static Charset charset = Charset.forName("UTF-8");
    private static CharsetEncoder encoder = charset.newEncoder();
    private static ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    private static ServerSocketChannel setup() throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        String host = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Listen at Host:" + host + ",port:" + 8125);
        InetSocketAddress address = new InetSocketAddress(host, 8125);
        channel.socket().bind(address);
        return channel;
    }

    private static void serve(ServerSocketChannel channel) throws IOException {
        try (SocketChannel socketChannel = channel.accept()) {
            String now = new Date().toString();
            socketChannel.write(encoder.encode(CharBuffer.wrap(now + "\r\n")));
            System.out.println(socketChannel.socket().getInetAddress() + " : " + now);
            socketChannel.close();
        }
    }

    public static void main(String[] args) throws IOException {
        for (; ; ) {
            serve(setup());
        }
    }
}
