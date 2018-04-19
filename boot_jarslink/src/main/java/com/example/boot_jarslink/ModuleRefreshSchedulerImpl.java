package com.example.boot_jarslink;

import com.alipay.jarslink.api.ModuleConfig;
import com.alipay.jarslink.api.impl.AbstractModuleRefreshScheduler;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/17
 */
@Service
public class ModuleRefreshSchedulerImpl extends AbstractModuleRefreshScheduler {
    @Override
    public List<ModuleConfig> queryModuleConfigs() {
        return null;
    }

    public static ModuleConfig buildModuleConfig() {

        URL demoModule = Thread.currentThread().getContextClassLoader().getResource("META-INF/spring/demo-1.0.0.jar");

        ModuleConfig moduleConfig = new ModuleConfig();
        moduleConfig.setName("demo");
        moduleConfig.setEnabled(true);
        moduleConfig.setVersion("1.0.0.20171621");
        moduleConfig.setProperties(ImmutableMap.of("svnPath", new Object()));
        moduleConfig.setModuleUrl(ImmutableList.of(demoModule));

        return moduleConfig;

    }
}
