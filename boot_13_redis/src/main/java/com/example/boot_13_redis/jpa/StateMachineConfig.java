package com.example.boot_13_redis.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/07/07
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends StateMachineConfigurerAdapter<OrderStatus, OrderStatusChangeEvent> {
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderStatusChangeEvent> states) throws Exception {
        states.withStates()
                .initial(OrderStatus.WAIT_PAYMENT) // 定义初始状态
                .states(EnumSet.allOf(OrderStatus.class)); // 定义所有状态
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderStatusChangeEvent> transitions) throws Exception {
        transitions.withExternal()
                .source(OrderStatus.WAIT_PAYMENT).target(OrderStatus.WAIT_DELIVER)
                .event(OrderStatusChangeEvent.PAYED).and()
                .withExternal()
                .source(OrderStatus.WAIT_DELIVER).target(OrderStatus.WAIT_RECEIVE)
                .event(OrderStatusChangeEvent.DELIVERY)
                .and()
                .withExternal()
                .source(OrderStatus.WAIT_RECEIVE).target(OrderStatus.FINISH)
                .event(OrderStatusChangeEvent.RECEIVED);
    }
}