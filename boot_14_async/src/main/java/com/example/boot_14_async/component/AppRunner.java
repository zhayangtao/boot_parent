package com.example.boot_14_async.component;

import com.example.boot_14_async.entity.User;
import com.example.boot_14_async.service.GitHubLoopupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import java.util.concurrent.Future;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/1/29
 */
public class AppRunner implements CommandLineRunner {

    public static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final GitHubLoopupService gitHubLoopupService;

    public AppRunner(GitHubLoopupService gitHubLoopupService) {
        this.gitHubLoopupService = gitHubLoopupService;
    }

    @Override
    public void run(String... strings) throws Exception {
        long start = System.currentTimeMillis();

        Future<User> user1 = gitHubLoopupService.findUser("PivotalSoftware");
        Future<User> user2 = gitHubLoopupService.findUser("CloudFoundry");
        Future<User> user3 = gitHubLoopupService.findUser("Spring-Projects");

        while (!user1.isDone() || !user2.isDone() || ! user3.isDone()) {
            Thread.sleep(10);
        }

        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        logger.info("--> " + user1.get());
        logger.info("--> " + user2.get());
        logger.info("--> " + user3.get());
    }
}
