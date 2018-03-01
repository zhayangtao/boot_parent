package com.example.boot.aspect;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/1/4
 */
public class DataSourceHandler {

    /**
     * 数据源名称线程池
     */
    private static final ThreadLocal<String> threadlocal = new ThreadLocal<>();

    /**
     * 项目启动时将配置的读写数据源加到holder中
     * @param datasource 数据源名称
     */
    public static void putDataSource(String datasource)  {
        threadlocal.set(datasource);
    }

    /**
     * 从threadLocal中获取数据源字符串
     * @return 返回数据源
     */
    public static String getDataSource() {
        return threadlocal.get();
    }
}
