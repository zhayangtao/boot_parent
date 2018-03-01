package com.example.boot.aspect;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.*;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/1/4
 */
public class ChooseDataSource extends AbstractRoutingDataSource {

    public static Map<String, List<String>> METHOD_TYPE_MAP = new HashMap<>();

    /**
     * 实现父类中的抽象方法，获取数据源名称
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHandler.getDataSource();
    }

    public void setMethodType(Map<String, String> map) {
        map.keySet().forEach(s -> {
            List<String> v = new ArrayList<>();
            String[] types = map.get(s).split(",");
            Arrays.stream(types).filter(StringUtils::isNotBlank).forEach(v::add);
            METHOD_TYPE_MAP.put(s, v);
        });
    }

}
