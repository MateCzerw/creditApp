package com.inteca.credit.services.listeners;

import com.inteca.credit.domain.Credit;
import model.CreditOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.statemachine.StateMachine;
//import org.springframework.statemachine.config.StateMachineFactory;
//import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateCreditManager {

    //todo implementation of saga

//    public static final String ORDER_ID_HEADER = "ORDER_ID";
//    public static final String ORDER_OBJECT_HEADER = "CREDIT_ORDER";
//
//    private final StateMachineFactory<CreateCreditStatusEnum, CreateCreditEventEnum> stateMachineFactory;
//
//    @Transactional
//    public CreditOrder newCreditOrder(CreditOrder creditOrder) {
//
//
//        Credit credit = creditOrder.getCredit();
//
//
//        beerOrder.setOrderStatus(BeerOrderStatusEnum.NEW);
//        BeerOrder savedOrder = beerOrderRepository.saveAndFlush(beerOrder);
//
//        //send validation event
//        sendBeerOrderEvent(savedOrder, BeerOrderEventEnum.VALIDATE_ORDER);
//
//        return savedOrder;
//    }
//
//    private void sendCreditOrderEvent(CreditOrder creditOrder, CreateCreditEventEnum event) {
//
//        StateMachine<CreateCreditStatusEnum, CreateCreditEventEnum> sm = build(creditOrder);
//
//        Message msg = MessageBuilder.withPayload(event)
//                .setHeader(ORDER_ID_HEADER, creditOrder.getId().toString())
//                .build();
//
//        sm.sendEvent(msg);
//    }
//
//    private StateMachine<CreateCreditStatusEnum, CreateCreditEventEnum> build(CreditOrder creditOrder) {
//
//        StateMachine<CreateCreditStatusEnum, CreateCreditEventEnum> sm = stateMachineFactory.getStateMachine(creditOrder.getId());
//
//        sm.stop();
//
//        sm.getStateMachineAccessor()
//                .doWithAllRegions(sma -> {
//                    sma.resetStateMachine(new DefaultStateMachineContext<>(creditOrder.getStatus(), null,
//                            null, null));
//                });
//
//        sm.getExtendedState().getVariables().put(ORDER_OBJECT_HEADER, creditOrder);
//
//        sm.start();
//
//        return sm;
//    }

}
