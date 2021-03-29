package com.inteca.credit.sender;

import com.inteca.credit.config.JmsConfig;
import model.Test;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void createCredit(){

        Test test = Test
                .builder()
                .id(UUID.randomUUID())
                .message("Create credit")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.CREATE_CREDIT, test);

    }

    @Scheduled(fixedRate = 2000)
    public void getCredit(){

        Test test = Test
                .builder()
                .id(UUID.randomUUID())
                .message("Get credit")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.GET_CREDIT, test);

    }



}
