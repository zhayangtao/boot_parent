package com.example.boot.nio;

import scala.Char;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/09
 */
public class CharsetExample {
    public static void main(String[] args) {
        Charset charset = Charset.forName("UTF-8");
        System.out.println(charset.displayName());
        System.out.println(charset.canEncode());
        String message = "Welcome, it is Charset test Example";

        ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes());
        CharBuffer charBuffer = charset.decode(byteBuffer);

        ByteBuffer byteBuffer1 = charset.encode(charBuffer);
        while (byteBuffer1.hasRemaining()) {
            System.out.print((char)byteBuffer1.get());
        }
        byteBuffer1.clear();
    }
}
