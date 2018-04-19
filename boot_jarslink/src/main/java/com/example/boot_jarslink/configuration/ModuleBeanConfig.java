package com.example.boot_jarslink.configuration;

import com.alipay.jarslink.api.ModuleLoader;
import com.alipay.jarslink.api.ModuleManager;
import com.alipay.jarslink.api.impl.ModuleLoaderImpl;
import com.alipay.jarslink.api.impl.ModuleManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/17
 */
@Configuration
public class ModuleBeanConfig {

    @Bean
    public ModuleLoader getModuleLoader() {
        return new ModuleLoaderImpl();
    }

    @Bean
    public ModuleManager getModuleManager() {
        return new ModuleManagerImpl();
    }
}
