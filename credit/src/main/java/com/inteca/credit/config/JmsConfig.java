package com.inteca.credit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 2019-09-07.
 */
@Component
public class JmsConfig {

    public static final String SAVE_CUSTOMER = "save-customer";
    public static final String SAVE_PRODUCT = "save-product";
    public static final String GET_CUSTOMERS = "get-customers";
    public static final String GET_PRODUCTS = "get-products";
    public static final String SEND_CUSTOMERS = "send-customers";
    public static final String SEND_PRODUCTS = "send-products";

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
