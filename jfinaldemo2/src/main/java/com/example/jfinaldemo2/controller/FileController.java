package com.example.jfinaldemo2.controller;

import com.jfinal.core.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/03/06
 */
public class FileController extends Controller {

    public void file() {
        String filePath = "D:/file/test.properties";//绝对路径
        File file = null;
        InputStreamReader reader = null;
        BufferedReader br = null;
        StringBuilder line = new StringBuilder();
        try {
            file = ResourceUtils.getFile("C:\\Users\\Administrator\\Desktop\\result.json");
            reader = new InputStreamReader(
                    new FileInputStream(file)); // 建立一个输入流对象reader
            br = new BufferedReader(reader);
            String s = "";
            while ((s = br.readLine()) != null) {
                line.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        renderJson(line.toString());
    }

    public void another() {
        String object = new RestTemplate().getForObject("http://localhost:8081/file/file", String.class);
        System.out.println(object);
        renderText("hello");
    }

}
