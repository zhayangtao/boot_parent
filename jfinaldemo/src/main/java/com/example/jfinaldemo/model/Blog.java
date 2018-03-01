package com.example.jfinaldemo.model;

import com.jfinal.plugin.activerecord.Model;

/**
 *
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/23
 */
public class Blog extends Model<Blog> {

    private static final long serialVersionUID = 4164001201379688146L;

    public static final Blog dao = new Blog();

    public User getUser() {
        return User.dao.findById(get("user_id"));
    }
}
