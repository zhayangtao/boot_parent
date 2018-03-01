package com.example.boot.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/1/4
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)// 使用cglib生成代理类
public class DataSourceAspect {

    @Pointcut("execution(* com.example.boot.*.*(..))")
    public void aspect() {
    }

    @Before("aspect()")
    public void before(JoinPoint point) {
        String className = point.getTarget().getClass().getName();
        String method = point.getSignature().getName();
//        ChooseDataSource.METHOD_TYPE_MAP.values().stream().filter();
        ChooseDataSource.METHOD_TYPE_MAP.keySet().forEach(s -> {
            ChooseDataSource.METHOD_TYPE_MAP.get(s).stream()
                    .filter(method::startsWith).forEach(DataSourceHandler::putDataSource);
        });
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.isNotBlank(null));
    }
}
