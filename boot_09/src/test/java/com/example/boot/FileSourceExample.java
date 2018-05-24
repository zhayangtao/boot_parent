package com.example.boot;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/10
 */
public class FileSourceExample {

    @Test
    public void testFileSource() {
        String filePath = "";
        WritableResource resource1 = new PathResource(filePath);
        Resource resource2 = new ClassPathResource("");
        try {
            OutputStream stream1 = resource1.getOutputStream();
            stream1.write("welcome".getBytes());
            stream1.close();
            InputStream inputStream1 = resource1.getInputStream();
            InputStream inputStream2 = resource2.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i;
            while ((i = inputStream1.read()) != -1) {
                byteArrayOutputStream.write(i);
            }
            System.out.println(byteArrayOutputStream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
