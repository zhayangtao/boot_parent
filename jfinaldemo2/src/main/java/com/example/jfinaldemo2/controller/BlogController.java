package com.example.jfinaldemo2.controller;

import com.example.jfinaldemo2.interceptor.BlogInterceptor;
import com.example.jfinaldemo2.model.Blog;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/02/27
 */
@Before(BlogInterceptor.class) // 配置拦截器
public class BlogController extends Controller {

    public void index() {
        renderText("this is my blog");
    }

    public void findBlogById() {
        int id = getParaToInt();
        Blog blog = Blog.dao.findById(id);
        renderJson(blog);
    }

    public void useRedis() {
        Cache cache = Redis.use("blog");
        cache.set("key", "value");
        Cache result = cache.get("key");
    }

}
