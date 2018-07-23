package com.example.boot_rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/13
 */
@RunWith(JUnit4.class)
public class HelloControllerTest {
    @Test
    public void test() {
        List<String> mylist = new ArrayList<>();
        mylist.add("1");

        change(mylist);
        System.out.println("mylist:" + mylist);
    }


    void change(List<String> list) {
        list = new ArrayList<>();
        System.out.println("list:" + list);
    }
}
