package com.example.boot_12_mybatis;

import com.example.boot_12_mybatis.controller.AccountController;
import com.example.boot_12_mybatis.dao.RedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@WebMvcTest(AccountController.class)
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class Boot12MybatisApplicationTests {

	@Autowired
	private RedisDao redisDao;

//	@Autowired
//	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRedis() {
		redisDao.setKey("name", "liuxiang");
		redisDao.setKey("age", "11");
		System.out.println(redisDao.getValue("name"));
		System.out.println(redisDao.getValue("age"));
	}

	/*@Test
	public void returnDefault() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andDo(document("home"));
	}*/
}
