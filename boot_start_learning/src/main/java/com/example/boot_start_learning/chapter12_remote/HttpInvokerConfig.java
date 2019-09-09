package com.example.boot_start_learning.chapter12_remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/30
 */
@Configuration
public class HttpInvokerConfig {

    @Autowired
    private SingerService singerService;

    @Bean(name = "/httpInvoker/singerService")
    public HttpInvokerServiceExporter httpInvokerServiceExporter() {
        HttpInvokerServiceExporter invokerServiceExporter = new HttpInvokerServiceExporter();
        invokerServiceExporter.setService(singerService);
        invokerServiceExporter.setServiceInterface(SingerService.class);
        return invokerServiceExporter;
    }


}
