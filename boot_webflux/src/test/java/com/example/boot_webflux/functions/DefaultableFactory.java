package com.example.boot_webflux.functions;

import java.util.function.Supplier;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/27
 */
public interface DefaultableFactory {

    static Defaultable crate(Supplier<Defaultable> supplier) {
        return supplier.get();
    }

    static void main(String[] args) {
        Defaultable defaultable = DefaultableFactory.crate(DefaultableImpl::new);
        System.out.println(defaultable.noRequired());

        defaultable = DefaultableFactory.crate(OverrideAbleImpl::new);
        System.out.println(defaultable.noRequired());
    }
}
