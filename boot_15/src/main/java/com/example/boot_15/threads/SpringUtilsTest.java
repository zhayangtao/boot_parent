package com.example.boot_15.threads;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/13
 */
public class SpringUtilsTest {

    @Test
    public void testLoadPropertiesResource() throws IOException {
        Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("application.properties"));
        assertEquals("jdbc:mysql:///sampledb", properties.getProperty("spring.datasource.url"));
    }

    @Test
    public void testStringUtils() {
        assertEquals("java", StringUtils.unqualify("co.wo.java"));
    }

}

