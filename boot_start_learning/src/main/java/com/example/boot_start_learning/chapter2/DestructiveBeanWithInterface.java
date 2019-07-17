package com.example.boot_start_learning.chapter2;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/11
 */
public class DestructiveBeanWithInterface {
    private File file;
    private String filePath;

    @PostConstruct
    public void afterPropertiesSet() throws IOException {
        System.out.println("initializing Bean afterPropertiesSet");
        if (filePath == null) {
            throw new IllegalArgumentException("you must specify the filepath property ");
        }
        this.file = new File(filePath);
        file.createNewFile();
        System.out.println("File exists: "+ file.exists());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroying Bean");
        if (!file.delete()) {
            System.out.println("Error: failed to delete file.");
        }
        System.out.println("File exists: " + file.exists());
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static void main(String[] args) {

    }
}
