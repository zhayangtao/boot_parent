package com.example;


import org.osgl.mvc.annotation.Action;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/12/26
 * POJO 绑定
 */
public class PojoTest {

    @Action("test/571")
    public Employee pojo571(Employee employee) {
        return employee;
    }
}

