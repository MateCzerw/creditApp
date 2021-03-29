package com.inteca.credit.sm.createCredit;


import com.inteca.credit.domain.CreateCreditEventEnum;
import com.inteca.credit.domain.CreateCreditStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@RequiredArgsConstructor
@Configuration
@EnableStateMachineFactory
public class CreateCreditStateConfig extends StateMachineConfigurerAdapter<CreateCreditStatusEnum, CreateCreditEventEnum> {

    @Override
    public void configure(StateMachineStateConfigurer<CreateCreditStatusEnum, CreateCreditEventEnum> states) throws Exception {
        states.withStates()
                .initial(CreateCreditStatusEnum.NEW)
                .states(EnumSet.allOf(CreateCreditStatusEnum.class))
                .end(CreateCreditStatusEnum.CREATE_CONSUMER)
                .end(CreateCreditStatusEnum.CREATE_PRODUCT);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<CreateCreditStatusEnum, CreateCreditEventEnum> transitions) throws Exception {
        transitions.withExternal()
                .source(CreateCreditStatusEnum.NEW).target(CreateCreditStatusEnum.CREATE_CONSUMER)
                .event(CreateCreditEventEnum.CREATE_CUSTOMER)
                .and().withExternal()
                .source(CreateCreditStatusEnum.CREATE_CONSUMER).target(CreateCreditStatusEnum.CREATE_PRODUCT)
                .event(CreateCreditEventEnum.CREATE_PRODUCT);
    }
}
