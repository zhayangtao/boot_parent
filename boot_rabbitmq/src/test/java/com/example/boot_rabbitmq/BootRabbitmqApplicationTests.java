package com.example.boot_rabbitmq;

import com.example.boot_rabbitmq.service.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootRabbitmqApplicationTests {

	@Autowired
	private HelloSender helloSender;

	@Test
	public void contextLoads() {
	}

	@Test
	public void hello() throws InterruptedException {
		helloSender.send();
		TimeUnit.SECONDS.sleep(1);
	}

	@Test
	public void topicTest() throws InterruptedException {
		helloSender.sendTopic1();
		helloSender.sendTopic2();
		TimeUnit.SECONDS.sleep(1);
	}

	@Test
	public void testSendFanout() throws InterruptedException {
		helloSender.sendFanout();
		TimeUnit.SECONDS.sleep(1);
	}

}
