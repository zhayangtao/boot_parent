package com.example.boot.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/09
 */
public class ScatterGatherIO {
    public static void main(String[] args) {
        String data = "Scattering and Gathering example shown";
        gatherBytes(data);
        scatterBytes(data);
    }

    /**
     * used for reading the bytes from buffers and write it to a file channel.
     * @param data
     */
    private static void gatherBytes(String data) {
        String relative = System.getProperty("user.dir");
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(8); // holding a random number
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(400); // holding a data that we want to write
        byteBuffer1.asIntBuffer().put(420);
        byteBuffer2.asCharBuffer().put(data);
        GatheringByteChannel gatheringByteChannel = createChannelInstance(relative + "/testout.txt", true);
        try {
            gatheringByteChannel.write(new ByteBuffer[]{byteBuffer1, byteBuffer2});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void scatterBytes(String data) {
        String relative = System.getProperty("user.dir");
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(400);
        ScatteringByteChannel scatteringByteChannel = createChannelInstance(relative + "/testout.txt", false);
        try {
            scatteringByteChannel.read(new ByteBuffer[]{byteBuffer, byteBuffer1});
        } catch (IOException e) {
            e.printStackTrace();
        }
        byteBuffer.rewind();
        byteBuffer1.rewind();
        int bufferOne = byteBuffer.asIntBuffer().get();
        String bufferTwo = byteBuffer1.asCharBuffer().toString();
        System.out.println(bufferOne);
        System.out.println(bufferTwo);
    }

    private static FileChannel createChannelInstance(String s, boolean b) {
        FileChannel fileChannel = null;
        try {
            if (b) {
                fileChannel = new FileOutputStream(s).getChannel();
            } else {
                fileChannel = new FileInputStream(s).getChannel();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileChannel;
    }
}
