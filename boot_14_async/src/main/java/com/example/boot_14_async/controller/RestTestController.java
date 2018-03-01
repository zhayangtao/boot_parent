package com.example.boot_14_async.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/2/6
 */
@RestController
@RequestMapping("/rest")
public class RestTestController {

    @RequestMapping("/{id}/{name}/{age}")
    public String restTest(@PathVariable(value = "id", required = false)int id,
                         @PathVariable(value = "name", required = false)String name,
                         @PathVariable(value = "age", required = false)int age) {
        System.out.println(id);
        System.out.println(name);
        System.out.println(age);
        return "id=" + id + ",name=" + name + ",age=" + age;
    }

    @RequestMapping("/sleep")
    public String tenTime() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "休眠5秒";
    }

    @RequestMapping("/sync")
    public String sync() {
        String url = "http://localhost:8082/rest/sleep";
        new RestTemplate().getForObject(url, String.class);
        return "同步调用";
    }

    @RequestMapping("/async")
    public String async() {
        String url = "http://localhost:8082/rest/sleep";
        ListenableFuture<ResponseEntity<String>> entity = new AsyncRestTemplate().getForEntity(url, String.class);
        entity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("failure...");
            }

            @Override
            public void onSuccess(ResponseEntity<String> stringResponseEntity) {
                System.out.println("success...");
            }
        });
        return "异步调用";
    }
}
