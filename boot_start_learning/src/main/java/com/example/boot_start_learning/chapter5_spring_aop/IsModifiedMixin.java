package com.example.boot_start_learning.chapter5_spring_aop;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/18
 */
public class IsModifiedMixin extends DelegatingIntroductionInterceptor implements IsModified {
    private boolean isModified = false;
    private final Map<Method, Method> methodCache = new HashMap<>();

    @Override
    public boolean isModified() {
        return isModified;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (!isModified) {
            if (invocation.getMethod().getName().startsWith("set")
                    && invocation.getArguments().length == 1) {
                Method getter = getGetter(invocation.getMethod());
                if (getter != null) {
                    Object newVal = invocation.getArguments();
                    Object oldVal = getter.invoke(invocation.getThis());
                    if (newVal == null && oldVal == null) {
                        isModified = false;
                    } else if (newVal == null) {
                        isModified = true;
                    } else if (oldVal == null) {
                        isModified = true;
                    } else {
                        isModified = !newVal.equals(oldVal);
                    }
                }
            }
        }
        return super.invoke(invocation);
    }

    private Method getGetter(Method method) {
        Method getter = methodCache.get(method);
        if (getter != null) {
            return getter;
        }

        String getterName = method.getName().replaceFirst("set", "get");
        try {
            getter = method.getDeclaringClass().getMethod(getterName);
            synchronized (methodCache) {
                methodCache.put(method, getter);
            }
            return getter;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
