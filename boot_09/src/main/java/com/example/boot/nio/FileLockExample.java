package com.example.boot.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/10
 */
public class FileLockExample {
    public static void main(String[] args) throws IOException {
        String input = "* end of the file";
        System.out.println("Input string to the test file is: " + input);
        ByteBuffer byteBuffer = ByteBuffer.wrap(input.getBytes());
        String fp = "README_COPY.md";
        Path path = Paths.get(fp);
        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        System.out.println("FileChannel is open for write and acquiring lock...");
        fileChannel.position(fileChannel.size() - 1);
        FileLock lock = fileChannel.lock();
        System.out.println("The lock is shared: " + lock.isShared());
        fileChannel.write(byteBuffer);
        fileChannel.close();
        System.out.println("Content Writing is complete. Therefore close the channel and release the lock.");
//        PrintFile.print(fp);

    }
}
