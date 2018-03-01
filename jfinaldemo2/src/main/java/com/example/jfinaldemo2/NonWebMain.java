package com.example.jfinaldemo2;

import com.example.jfinaldemo2.model.User;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/27
 * 非 web 环境下使用 ActiveRecord
 */
public class NonWebMain {
    private static final String URL = "jdbc:mysql:///zytdb";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        DruidPlugin plugin = new DruidPlugin(URL, USER, PASSWORD);
        ActiveRecordPlugin recordPlugin = new ActiveRecordPlugin(plugin);
        recordPlugin.addMapping("user", User.class);
        // 需要调用一次 start() 方法
        plugin.start();
        recordPlugin.start();

        new User().set("username", "杜甫").set("age", 55).save();

        System.out.println(User.dao.findById(2));
    }
}
