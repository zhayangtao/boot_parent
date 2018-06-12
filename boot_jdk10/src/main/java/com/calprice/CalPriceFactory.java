package com.calprice;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/30
 */
public class CalPriceFactory {
    private static final String CAL_PRICE_PACKAGE = "com.calprice";

    private ClassLoader classLoader = getClass().getClassLoader();

    private List<Class<? extends CalPrice>> calPriceList;

    CalPrice createCalPrice(Customer customer) {
        for (Class<? extends CalPrice> aClass : calPriceList) {
            TotalValidRegion validRegion = handleAnnotation(aClass);
            assert validRegion != null;
            if (customer.getTotalAmount() > validRegion.min() && customer.getTotalAmount() < validRegion.max()) {
                try {
                    return aClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("errror");
                }
            }
        }
        throw new RuntimeException("error");
    }

    private TotalValidRegion handleAnnotation(Class<? extends CalPrice> aClass) {
        Annotation[] annotations = aClass.getDeclaredAnnotations();
        if (annotations == null || annotations.length == 0) {
            return null;
        }
        for (Annotation annotation : annotations) {
            if (annotation instanceof TotalValidRegion) {
                return (TotalValidRegion) annotation;
            }
        }
        return null;
    }

    private CalPriceFactory() {

    }

    static CalPriceFactory getInstance() {
        return CalPriceFactoryInstance.instance;
    }

    public static class CalPriceFactoryInstance {
        private static CalPriceFactory instance = new CalPriceFactory();
    }

    // 工厂初始化时要初始化策略列表
    private void init() {
        calPriceList = new ArrayList<>();
        File[] resources = getResources();
    }

    private File[] getResources() {
        try {
            File file = new File(Objects.requireNonNull(classLoader.getResource(CAL_PRICE_PACKAGE.replace(".", "/"))).toURI());
            return file.listFiles(pathname -> pathname.getName().endsWith(".class"));
        } catch (URISyntaxException e) {
            throw new RuntimeException("errrrrror");
        }
    }
}
