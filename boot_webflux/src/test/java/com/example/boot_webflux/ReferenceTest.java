package com.example.boot_webflux;

import java.lang.ref.WeakReference;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/05
 */
public class ReferenceTest {
    public static void main(String[] args) {
        String str = new String("java");
        WeakReference weakReference = new WeakReference(str);
        str = null;
        System.out.println(weakReference.get());
        System.gc();
        System.runFinalization();
        System.out.println(weakReference.get());

    }
}
