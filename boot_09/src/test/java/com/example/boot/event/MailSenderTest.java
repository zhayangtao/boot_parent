package com.example.boot.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class MailSenderTest {

    @Autowired
    private MailSender mailSender;

    @Test
    public void test() {
        mailSender.sendMail("aaa@bbb.com");
    }
}