package com.example.boot_14_async.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/1/29
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String name;
    private String blog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }
}
