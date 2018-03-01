package com.example.boot.entity;

import java.io.Serializable;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2017/12/29
 */
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -3868779168460560447L;

    private Long id;
    private String userName;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
