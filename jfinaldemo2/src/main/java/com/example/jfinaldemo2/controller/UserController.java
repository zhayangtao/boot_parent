package com.example.jfinaldemo2.controller;

import com.example.jfinaldemo2.interceptor.UserInterceptor;
import com.example.jfinaldemo2.model.User;
import com.example.jfinaldemo2.validator.LoginValidator;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.jfinal.plugin.activerecord.tx.Tx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/26
 */
@Before(UserInterceptor.class) // 配置类拦截器
public class UserController extends Controller {

    @Clear // 清除方法层上的拦截器（global、inject、class）
    public void index() {
        System.out.println("index");
        renderText("hello UserController");
    }

    public void show() {
        System.out.println("show");
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        renderJson(map);
    }

    public void findById2() {
        System.out.println("findById with para");
        int id = getParaToInt();
        System.out.println(getParaToInt("id"));// 支持非 rest 请求 ?id=xx
        User user = User.dao.findById(id);
        renderJson("user", user);
    }

    public void findById() {
        System.out.println("findById without para");
        User user = User.dao.findById(1);
        renderJson("user", user);
    }

    public void save() {
        System.out.println("save");
        String username = getPara("username");
        System.out.println(username);
        System.out.println("HttpKit.readData(getRequest()):" + HttpKit.readData(getRequest()));

        renderJson(1);
    }

    @Before(Tx.class)
    public void saveUseDBRecord() {
        Record record = new Record().set("username", "James").set("age", 35);
        Db.save("user", record);
        render("success");
    }

    /**
     * 使用 user.sql 中的语句查询
     */
    public void findUserById() {
        String sql = Db.getSql("findUserById");
        int id = getParaToInt();
        List<Record> records = Db.find(sql, id);
        renderJson(records);
    }

    /**
     * user.sql 中使用 #para(0) 后需要使用 getSqlPara 方法
     */
    public void findUserByIdWithPara() {
        int id = getParaToInt(0);
        SqlPara sqlPara = Db.getSqlPara("findUserByIdWithPara", id);
        List<Record> records = Db.find(sqlPara);
        renderJson(records);
    }

    /**
     * user.sql 中使用 #para(id) 后需要使用 getSqlPara 方法
     */
    public void findUserByIdWithPara2() {
        int id = getParaToInt(0);
        Kv cond = Kv.by("id", id);
        SqlPara sqlPara = Db.getSqlPara("findUserByIdWithPara2", cond);
        renderJson(Db.find(sqlPara));
    }

    /**
     * user.sql 中使用模糊查询
     */
    public void findUserByName() {

        String username = getPara(0);
        Kv cond = Kv.by("username", username);
        SqlPara sqlPara = Db.getSqlPara("findUserByName", cond);
        renderJson(Db.find(sqlPara));
    }

    public void findNFUserByName() {
        String username = getPara(0);
        Kv cond = Kv.by("username", username);
        SqlPara sqlPara = Db.getSqlPara("findNFUserByName", cond);
        renderJson(Db.find(sqlPara));
    }

    @Before(LoginValidator.class)
    public void login() {

    }
}
