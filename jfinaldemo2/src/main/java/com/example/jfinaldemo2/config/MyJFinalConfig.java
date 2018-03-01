package com.example.jfinaldemo2.config;

import com.example.jfinaldemo2.controller.BlogController;
import com.example.jfinaldemo2.controller.UserController;
import com.example.jfinaldemo2.model.Blog;
import com.example.jfinaldemo2.model.User;
import com.jfinal.config.*;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.template.Engine;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/26
 * 配置 jfinal
 */
public class MyJFinalConfig extends JFinalConfig {
    @Override
    public void configConstant(Constants me) {

        me.setDevMode(true); // 开启开发模式：sql文件动态加载，打印访问路径等
    }

    @Override
    public void configRoute(Routes me) {

        // 第三个参数为空时默认为 controllerKey
        me.add("/user", UserController.class);
        me.add("/blog", BlogController.class);
    }


    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {

        loadPropertyFile("db.properties");
        // 配置访问数据库
        DruidPlugin druidPlugin = new DruidPlugin(getProperty("mysqljdbcUrl"), getProperty("user"), getProperty("password"));
        me.add(druidPlugin);

        // 使用 ActiveRecord 插件操作 mysql 数据库
        ActiveRecordPlugin mysqlPlugin = new ActiveRecordPlugin("mysql", druidPlugin);
        // 建立数据库表名到 Model 的映射关系，若不设置主键则默认主键名为 id
        mysqlPlugin.addMapping("user", User.class);

        mysqlPlugin.setBaseSqlTemplatePath(PathKit.getRootClassPath() + "/sql");
        mysqlPlugin.addSqlTemplate("user.sql");
        me.add(mysqlPlugin);

        // 使用 postgresql 数据库
        DruidPlugin postgres = new DruidPlugin(getProperty("postgresUrl"), getProperty("postgresuser"), getProperty("postgrespassword"));
        me.add(postgres);
        ActiveRecordPlugin postgresPlugin = new ActiveRecordPlugin("postgres", postgres);
        postgresPlugin.setDialect(new PostgreSqlDialect());
        postgresPlugin.setBaseSqlTemplatePath(PathKit.getRootClassPath() + "/sql");
        postgresPlugin.addSqlTemplate("blog.sql");
        postgresPlugin.addMapping("blog", Blog.class);
        me.add(postgresPlugin);

        // 配置 redis 缓存
        RedisPlugin redisPlugin = new RedisPlugin("blog", "localhost");
        me.add(redisPlugin);


    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }
}
