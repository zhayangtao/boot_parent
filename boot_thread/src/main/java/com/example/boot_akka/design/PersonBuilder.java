package com.example.boot_akka.design;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/05/23
 */
public interface PersonBuilder {
    void builderHead();

    void builderLHand();

    void builderRHand();

    void builderLFoot();

    void builderRFoot();

    Product getResult();
}
