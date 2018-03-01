package com.example.jfinaldemo2.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/26
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1802075027932788613L;
    public static final User dao = new User().dao();
}
