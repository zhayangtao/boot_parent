package com.example.boot_14_async.service;

import com.example.boot_14_async.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/1/29
 */
@Service
public class GitHubLoopupService {

    private static final Logger logger = LoggerFactory.getLogger(GitHubLoopupService.class);

    private final RestTemplate restTemplate;

    public GitHubLoopupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Future<User> findUser(String user) throws InterruptedException {
        logger.info("looking up " + user);
        String url = String.format("https://api.github.com/users/%s", user);
        User result = restTemplate.getForObject(url, User.class);
        Thread.sleep(1000L);
        return new AsyncResult<>(result);
    }

}
