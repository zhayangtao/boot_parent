package com.example.boot_akka.design;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/05/23
 */
public class SqlServerDriver implements Driver {
    @Override
    public void connect() {
        System.out.println("链接SqlServer数据库");
    }
}
