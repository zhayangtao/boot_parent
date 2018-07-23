package com.example.boot.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/09
 */
public class CharsetExample2 {
    public static void main(String[] args) throws CharacterCodingException {
        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharsetEncoder charsetEncoder = charset.newEncoder();
        String message = "Example of Encode and Decode i Java NIO";
        ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes());
        CharBuffer charBuffer = charsetDecoder.decode(byteBuffer);
        ByteBuffer byteBuffer1 = charsetEncoder.encode(charBuffer);
        while (byteBuffer1.hasRemaining()) {
            System.out.print((char) byteBuffer1.get());
        }
    }
}
