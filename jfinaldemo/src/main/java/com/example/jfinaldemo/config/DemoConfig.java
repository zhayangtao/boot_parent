package com.example.jfinaldemo.config;

import com.example.jfinaldemo.controller.HelloController;
import com.example.jfinaldemo.interceptor.DemoInterceptor;
import com.example.jfinaldemo.model.Blog;
import com.example.jfinaldemo.model.User;
import com.jfinal.config.*;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.cache.EhCache;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.activerecord.tx.TxByActionKeyRegex;
import com.jfinal.plugin.activerecord.tx.TxByActionKeys;
import com.jfinal.plugin.activerecord.tx.TxByMethodRegex;
import com.jfinal.plugin.activerecord.tx.TxByMethods;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/2/22
 */
public class DemoConfig extends JFinalConfig {
    /**
     * 配置JFinal常量值
     *
     * @param me
     */
    @Override
    public void configConstant(Constants me) {
        //开发模式下，JFinal会对每次请求输出报告，如请求的URL，Controller
        me.setDevMode(true);
    }

    /**
     * 配置JFinal访问路由
     * 通 过 以 下 的 配 置 ，
     * http://localhost/hello 将访问 HelloController.index() 方法 ， 而
     * http://localhost/hello/methodName 将访问到 HelloController.methodName()方法
     *
     * @param me
     */
    @Override
    public void configRoute(Routes me) {
        //当 view  以 “/”  字符 打头时 表示绝对路径， ，baseViewPath  与 viewPath  将被忽略
        me.add("/hello", HelloController.class);

//        me.setBaseViewPath("/view");
//        me.addInterceptor()
    }

    /**
     * 此方法用来配置 Template Engine
     *
     * @param me
     */
    @Override
    public void configEngine(Engine me) {

    }

    /**
     * 此方法用来配置JFinal的Plugin，如下代码配置了Druid数据库连接池插件与ActiveRecord
     * 数据库访问插件。通过以下的配置，可以在应用中使用 ActiveRecord 非常方便地操作数据库。
     *
     * @param me
     */
    @Override
    public void configPlugin(Plugins me) {
        loadPropertyFile("myConfig.properties");
        DruidPlugin plugin = new DruidPlugin(getProperty("jdbcUrl"),
                getProperty("user"), getProperty("password"));
        me.add(plugin);

        ActiveRecordPlugin recordPlugin = new ActiveRecordPlugin(plugin);
        me.add(recordPlugin);
        // 建立数据库表名到Model的映射关系
        recordPlugin.addMapping("user", User.class);
        recordPlugin.setDialect(new MysqlDialect());
        recordPlugin.setBaseSqlTemplatePath(PathKit.getRootClassPath());
        recordPlugin.addSqlTemplate("demo.sql");

        // 添加表关联的复合主键
//        recordPlugin.addMapping("user_role", "userId, roleId", Foo.class);

        // 配置多数据源
        DruidPlugin mysql = new DruidPlugin(getProperty("jdbcUrl"),
                getProperty("user"), getProperty("password"));
        me.add(mysql);
        ActiveRecordPlugin mysqlPlugin = new ActiveRecordPlugin("mysql", mysql);
        me.add(mysqlPlugin);
        mysqlPlugin.setCache(new EhCache());
        mysqlPlugin.addMapping("user", User.class);

        DruidPlugin postgres = new DruidPlugin(getProperty("jdbcUrl"),
                getProperty("user"), getProperty("password"));
        ActiveRecordPlugin postgresPlugin = new ActiveRecordPlugin("postgres", postgres);
        me.add(postgres);
        postgresPlugin.setDialect(new PostgreSqlDialect());
        postgresPlugin.setTransactionLevel(8);
        postgresPlugin.addMapping("blog", Blog.class);

    }

    /**
     * 此方法用来配置 JFinal 的全局拦截器，全局拦截器将拦截所有 action 请求，除非使用
     * @Clear 在 Controller 中清除
     * @param me
     *
     */
    @Override
    public void configInterceptor(Interceptors me) {

        me.addGlobalActionInterceptor(new DemoInterceptor()); // 配置全局拦截去，拦截所有Action方法
        me.addGlobalServiceInterceptor(new DemoInterceptor()); // 拦截所有业务层方法

        me.add(new TxByMethodRegex("(.*save.*|.*update.*)")); // 添加事务
        me.add(new TxByMethods("save", "update"));// 添加事务

        me.add(new TxByActionKeyRegex("/trans.*"));// 添加事务
        me.add(new TxByActionKeys("/tx/save", "/tx/update"));// 添加事务
    }

    /**
     * 此方法用来配置JFinal的Handler，Handler
     * 可以接管所有 web 请求，并对应用拥有完全的控制权，可以很方便地实现更高层的功能性扩
     * 展。
     *
     * @param me
     */
    @Override
    public void configHandler(Handlers me) {

    }
}
