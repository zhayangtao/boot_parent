package com.calprice.designmodel.decorator;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/11
 */
public class Decorator extends Component {

    private Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        if (component != null) {
            component.operation();
        }
    }
}
