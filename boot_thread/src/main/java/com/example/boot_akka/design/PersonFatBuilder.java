package com.example.boot_akka.design;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/05/23
 */
public class PersonFatBuilder implements PersonBuilder {
    private Product product = new Product();
    @Override
    public void builderHead() {
        product.add("fat head");
    }

    @Override
    public void builderLHand() {
        product.add("fat LHand");
    }

    @Override
    public void builderRHand() {

    }

    @Override
    public void builderLFoot() {

    }

    @Override
    public void builderRFoot() {

    }

    @Override
    public Product getResult() {
        return null;
    }
}
