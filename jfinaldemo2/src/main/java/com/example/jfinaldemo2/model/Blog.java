package com.example.jfinaldemo2.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/27
 */
public class Blog extends Model<Blog> {
    public static final Blog dao = new Blog().dao();
}
