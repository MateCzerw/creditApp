package com.inteca.credit.sm.getCredits;


import com.inteca.credit.domain.GetCreditsEventEnum;
import com.inteca.credit.domain.GetCreditsStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
//import org.springframework.statemachine.config.EnableStateMachineFactory;
//import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
//import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
//import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@RequiredArgsConstructor
//@Configuration
//@EnableStateMachineFactory
public class GetCreditsStateConfig {

//        extends StateMachineConfigurerAdapter<GetCreditsStatusEnum, GetCreditsEventEnum> {
//
//    @Override
//    public void configure(StateMachineStateConfigurer<GetCreditsStatusEnum, GetCreditsEventEnum> states) throws Exception {
//        states.withStates()
//                .initial(GetCreditsStatusEnum.INITIAL)
//                .states(EnumSet.allOf(GetCreditsStatusEnum.class))
//                .end(GetCreditsStatusEnum.GET_CREDITS)
//                .end(GetCreditsStatusEnum.GET_CUSTOMERS)
//                .end(GetCreditsStatusEnum.GET_PRODUCTS);
//
//    }
//
//    @Override
//    public void configure(StateMachineTransitionConfigurer<GetCreditsStatusEnum, GetCreditsEventEnum> transitions) throws Exception {
//        transitions.withExternal()
//                .source(GetCreditsStatusEnum.INITIAL).target(GetCreditsStatusEnum.GET_PRODUCTS)
//                .event(GetCreditsEventEnum.GET_PRODUCTS)
//                .and().withExternal()
//                .source(GetCreditsStatusEnum.GET_PRODUCTS).target(GetCreditsStatusEnum.GET_CUSTOMERS)
//                .event(GetCreditsEventEnum.GET_CUSTOMERS)
//                .and().withExternal()
//                .source(GetCreditsStatusEnum.GET_CUSTOMERS).target(GetCreditsStatusEnum.GET_PRODUCTS)
//                .event(GetCreditsEventEnum.GET_PRODUCTS);
//    }
}
