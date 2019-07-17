package com.example.boot_start_learning.chapter5_spring_aop;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/16
 */
public class ErrorBean {
    public void errorProneMethod() throws Exception {
        throw new Exception("Generic Exception");
    }

    public void otherErrorProneMethod() {
        throw new IllegalArgumentException("IllegalArgument Exception");
    }
}
