package com.example.jfinaldemo.controller;

import com.example.jfinaldemo.interceptor.DemoInterceptor;
import com.example.jfinaldemo.model.Blog;
import com.example.jfinaldemo.validator.LoginValidator;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;

import java.util.List;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/23
 */
@Before(DemoInterceptor.class)
public class UserController extends Controller {

    @Clear // 清除之前的方法拦截如：DemoInterceptor
    @Before(LoginValidator.class)
    public void login() {

    }

    //此方法被 DemoInterceptor 拦截
    // 访问路径 /login，会忽略 DemoConfig 中配置的路径
    @ActionKey("/login")
    public void show() {

    }

    // 清除指定的拦截器，只清除本层以上拦截器，无法清除本层以下的拦截器
    @Clear({DemoInterceptor.class})
    public void find() {

    }

    @Before(Tx.class) // 声明式事务
    public void trans_demo() {
        Integer transAmount = getParaToInt("transAmount");
        Db.update("update account set cash = cash - ? where id = ?", transAmount, 1);
        Db.update("update account set cash = cash + ? where id = ?", transAmount, 1);
    }

    public void textCache() {
        /*
        cacheName 需要在 ehcache.xml 中配置
         */
        List<Blog> blogs = Blog.dao.findByCache("cacheName", "key", "select * from blog");
        setAttr("blogList", blogs).render("list.html");
    }

    public void relation() {
        String sql = "select b.*, u.user_name from blog b inner join user u on b.user_id = u.id where b.id=?";
        Blog blog = Blog.dao.findFirst(sql, 123);
        String name = blog.getStr("user_name");
    }

    public void compositeKey() {
        // 1、在 DemoConfig 中配置复合主键关系
        // 对于 Db + Record 模式不需要配置复合主键关系
        Db.findById("user_role", "roleId, userId", 123, 456);
        Db.deleteById("user_role", "roleId, userId", 123, 456);
    }

}
