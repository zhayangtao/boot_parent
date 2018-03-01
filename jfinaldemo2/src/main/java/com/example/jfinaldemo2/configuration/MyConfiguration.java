package com.example.jfinaldemo2.configuration;

import com.jfinal.core.JFinalFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/26
 * 配置 JFinalFilter 以及初始化配置参数
 */
@Configuration
public class MyConfiguration{

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JFinalFilter()); // 设置 filter
        registrationBean.addInitParameter("configClass", "com.example.jfinaldemo2.config.MyJFinalConfig");
        registrationBean.addUrlPatterns("/*"); // 设置过滤路径
        return registrationBean;
    }
}
