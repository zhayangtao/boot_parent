package com.example.boot_mybatis_plus.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/22
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * mybatis-plus 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        // 开启 PageHelper 支持
        interceptor.setLocalPage(true);
        return interceptor;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.example.boot_mybatis_plus.mapper*");
        return scannerConfigurer;
    }
}
