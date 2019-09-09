package com.example.boot_start_learning.chapter14;

import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * @author no one
 * @version 1.0
 * @since 2019/08/02
 */
@Component
@ManagedResource(description = "JMX managed resource", objectName = "jmxDemo:name=ProSpring")
public class AppStatisticsImpl implements AppStatistics {

    @Override
    public int getTotalSingerCount() {
        return 0;
    }
}
