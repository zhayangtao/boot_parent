package com.example.boot_dubbo_provider.service;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/03/20
 */
@Service(version = "1.0.0", application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}")
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
