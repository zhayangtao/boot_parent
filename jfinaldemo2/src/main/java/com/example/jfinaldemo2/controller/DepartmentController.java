package com.example.jfinaldemo2.controller;

import com.jfinal.core.Controller;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/03/08
 */
public class DepartmentController extends Controller {

    public void index() {
        String object = new RestTemplate().getForObject("http://localhost:8080/file/file", String.class);
        renderJson(object);
    }
}
