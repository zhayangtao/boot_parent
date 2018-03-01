package com.example.jfinaldemo.filters;

import com.jfinal.core.JFinalFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/2/22
 */
@Configuration
public class MyFilter {

    @Bean
    public FilterRegistrationBean registrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new JFinalFilter());
        bean.addInitParameter("configClass", "com.example.jfinaldemo.config.DemoConfig");
        bean.addUrlPatterns("/*");
        return bean;
    }
}
