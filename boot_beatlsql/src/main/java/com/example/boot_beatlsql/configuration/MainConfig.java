package com.example.boot_beatlsql.configuration;

import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/1/24
 */
@Configuration
public class MainConfig {

    @Bean(initMethod = "init", value = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration configuration = new BeetlGroupUtilConfiguration();
        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
        try {
            WebAppResourceLoader loader = new WebAppResourceLoader(resolver.getResource("classpath:/templates").getFile().getPath());
            configuration.setResourceLoader(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configuration;
    }

    @Bean(value = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig")BeetlGroupUtilConfiguration configuration) {
        BeetlSpringViewResolver resolver = new BeetlSpringViewResolver();
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setOrder(0);
        resolver.setConfig(configuration);
        return resolver;
    }

    //配置包扫描
    @Bean(value = "beetlSqlScannerConfigurer")
    public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer() {
        BeetlSqlScannerConfigurer configurer = new BeetlSqlScannerConfigurer();
        configurer.setBasePackage("com.example.dao");
//        configurer.setDaoSuffix("Dao");
        configurer.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
        return configurer;
    }

    @Bean
    public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("datasource")DataSource dataSource) {
        SqlManagerFactoryBean factoryBean = new SqlManagerFactoryBean();
        BeetlSqlDataSource sqlDataSource = new BeetlSqlDataSource();
        sqlDataSource.setMasterSource(dataSource);
        factoryBean.setCs(sqlDataSource);
        factoryBean.setDbStyle(new MySqlStyle());
        factoryBean.setInterceptors(new Interceptor[]{new DebugInterceptor()});
        factoryBean.setNc(new UnderlinedNameConversion());//开启驼峰
        factoryBean.setSqlLoader(new ClasspathLoader("sql"));//sql文件路径
        return factoryBean;
    }

    @Bean(value = "datasource")
    public DataSource getDataSource() {
        return DataSourceBuilder.create().url("jdbc:///sampledb").username("root").password("1234").build();
    }

    @Bean(value = "txManager")
    public DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("datasource") DataSource dataSource) {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }
}
