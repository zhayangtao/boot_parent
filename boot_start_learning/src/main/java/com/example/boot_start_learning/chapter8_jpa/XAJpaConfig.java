package com.example.boot_start_learning.chapter8_jpa;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/28
 * 分布式事务
 */
@Configuration
@EnableJpaRepositories
public class XAJpaConfig {
    private static Logger logger = LoggerFactory.getLogger(XAJpaConfig.class);

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSourceA() {
        try {
            AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
            dataSource.setUniqueResourceName("XADBMSA");
            dataSource.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
            dataSource.setXaProperties(xaAProperties());
            dataSource.setPoolSize(1);
            return dataSource;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    public Properties xaAProperties() {
        Properties properties = new Properties();
        properties.put("databaseName", "musicdb_a");
        properties.put("user", "prospring5_a");
        properties.put("password", "prospring5_a");
        return properties;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSourceB() {
        try {
            AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
            dataSource.setUniqueResourceName("XADBMSB");
            dataSource.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
            dataSource.setXaProperties(xaBProperties());
            dataSource.setPoolSize(1);
            return dataSource;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    public Properties xaBProperties() {
        Properties properties = new Properties();
        properties.put("databaseName", "musicdb_b");
        properties.put("user", "prospring5_b");
        properties.put("password", "prospring5_b");
        return properties;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.transaction.factory_class", "org.hibernate.");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.jdbc.max_fetch_depth", 3);
        properties.put("hibernate.jdbc.fetch_size", 50);
        properties.put("hibernate.jdbc.batch_size", 10);
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.use_sql_comments", true);
        return properties;
    }


}
