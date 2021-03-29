package com.inteca.product.listeners;



import com.inteca.product.config.JmsConfig;

import model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 2019-09-09.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ProductResultListener {


    @JmsListener(destination = JmsConfig.GET_CREDIT)
    public void listen(HelloWorldMessage message) {

        System.out.println(message.getMessage());

    }
}
