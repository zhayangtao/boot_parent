package com.calprice.designmodel.decorator;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/11
 */
public class ConcreteComponent extends Component {
    @Override
    public void operation() {
        System.out.println("具体对象的操作");
    }
}
