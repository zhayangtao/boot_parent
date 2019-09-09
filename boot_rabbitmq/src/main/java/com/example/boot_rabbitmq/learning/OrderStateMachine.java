package com.example.boot_rabbitmq.learning;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author no one
 * @version 1.0
 * @since 2019/09/03
 */
public enum OrderStateMachine {
    /**
     * 调度中
     */
    DISPATCHING {
        @Override
        public OrderStateMachine prevState() {
            return this;
        }

        @Override
        public OrderStateMachine nextState() {
            return DELIVERING;
        }
    },

    DELIVERING {
        @Override
        public OrderStateMachine prevState() {
            return DISPATCHING;
        }

        @Override
        public OrderStateMachine nextState() {
            return RECEIVED;
        }
    },
    /**
     * 收货
     */
    RECEIVED {
        @Override
        public OrderStateMachine prevState() {
            return DELIVERING;
        }

        @Override
        public OrderStateMachine nextState() {
            return RECEIVED;
        }
    };

    /**
     * 上一个状态
     * @return
     */
    public abstract OrderStateMachine prevState();

    /**
     * 下一个状态
     * @return
     */
    public abstract OrderStateMachine nextState();
}

@Data
class Order {
    private OrderStateMachine stateMachine = OrderStateMachine.DISPATCHING;
    private String name;

    private BigDecimal amount;

    public Order nextState() {
        this.stateMachine = this.stateMachine.nextState();
        return this;
    }

    public void log() {
        System.out.println(this.stateMachine.prevState() + "------------->" + this.stateMachine.name());
    }

    public static void main(String[] args) {
        Order order = new Order();

        order.setAmount(BigDecimal.TEN);
        order.setName("肥皂");

        order.nextState();
        order.log();
        order.nextState();
        order.log();
    }
}