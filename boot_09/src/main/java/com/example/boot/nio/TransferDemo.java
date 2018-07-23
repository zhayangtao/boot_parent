package com.example.boot.nio;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/09
 */
public class TransferDemo {
    public static void main(String[] args) throws IOException {
        String relative = System.getProperty("user.dir");
        String[] inputFiles = new String[]{relative + "/input1.txt", relative + "/input2.txt",
                relative + "/input3.txt", relative + "/input4.txt"};
        String outputFile = relative + "/combine_output.txt";
        FileOutputStream outputStream = new FileOutputStream(new File(outputFile));
        WritableByteChannel byteChannel = outputStream.getChannel();
        for (String inputFile : inputFiles) {
            FileInputStream inputStream = new FileInputStream(inputFile);
            FileChannel fileChannel = inputStream.getChannel();
            fileChannel.transferTo(0, fileChannel.size(), byteChannel);
            fileChannel.close();
            inputStream.close();
        }
        byteChannel.close();
        outputStream.close();
        System.out.println("done");
    }
}
