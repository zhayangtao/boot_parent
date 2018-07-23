package com.example.boot.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/09
 */
public class ChannelDemo {
    public static void main(String[] args) throws IOException {
        String relativePath = System.getProperty("user.dir");
        System.out.println(relativePath);
        FileInputStream inputStream = new FileInputStream(relativePath + "/README.md");
        ReadableByteChannel fileChannel = inputStream.getChannel();
        FileOutputStream outputStream = new FileOutputStream(relativePath + "/README_COPY.md");
        WritableByteChannel outChannel = outputStream.getChannel();
        copyData(fileChannel, outChannel);
        fileChannel.close();
        outChannel.close();
    }

    private static void copyData(ReadableByteChannel fileChannel, WritableByteChannel outChannel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(20 * 1024);
        while (fileChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                outChannel.write(byteBuffer);
            }
            byteBuffer.clear();
        }
    }
}
