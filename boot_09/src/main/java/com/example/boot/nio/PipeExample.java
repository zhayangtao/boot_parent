package com.example.boot.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/09
 */
public class PipeExample {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();
        String message = "Data is successfully sent for checking the java NIO Channel Pipe";
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        byteBuffer.clear();
        byteBuffer.put(message.getBytes());
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            sinkChannel.write(byteBuffer);
        }
        Pipe.SourceChannel sourceChannel = pipe.source();
        byteBuffer = ByteBuffer.allocate(512);
        while (sourceChannel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                char data = (char) byteBuffer.get();
                System.out.print(data);
            }
            byteBuffer.clear();
        }
    }
}
