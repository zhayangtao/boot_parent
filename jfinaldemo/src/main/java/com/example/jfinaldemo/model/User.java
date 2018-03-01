package com.example.jfinaldemo.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/23
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 7880712685234082732L;

    public static final User dao = new User().dao();

    public List<Blog> getBlogs() {
        return Blog.dao.find("select * from blog where user_id=?", get("id"));
    }

    public void test() {
        new User().set("username", "James").set("age", 25).save();
        User.dao.deleteById(25);

        User.dao.findById(2).set("username", "James").update();
        // 查询id为2的user， 且仅取 username 和 age 两个字段的值
        User user = User.dao.findByIdLoadColumns(2, "username, age");

        String username = user.getStr("username");
        Integer age = user.getInt("age");

        //查询所有年级大于13岁的user
        List<User> users = User.dao.find("select * from user where age>18");

        // 分页查询年龄大于18的user，当前页号为1，每页10条
        Page<User> userPage = User.dao.paginate(1, 10,
                "select *", "from user where age>?", 18);
    }

    /**
     * record 是 jFinal 中一个通用的 model
     */
    public void testRecord() {
        Record user = new Record().set("username", "James").set("age", 25);
        Db.save("user", user);

        Db.deleteById("user", 2);

        user = Db.findById("user", 25).set("username", "James");
        Db.update("user", user);

        // 获取 user 的 name 属性
        String username = user.getStr("username");
        Integer ager = user.getInt("age");

        List<Record> records = Db.find("select * from user where age>18");

        boolean succeed = Db.tx(() -> {
            int count = Db.update("update account set cash = cash - ? where id = ?", 100, 123);
            int count2 = Db.update("update account set cash = cash + ? where id = ?", 100, 456);
            return count == 1 && count2 == 1;
        });
    }
}
