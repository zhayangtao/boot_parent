package com.calprice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TotalValidRegion {

    int max() default Integer.MAX_VALUE;
    int min() default Integer.MIN_VALUE;
}
