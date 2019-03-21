package com.example.boot_17.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/13
 */
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> advice() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("fail", "fail");
        return resultMap;
    }
}
