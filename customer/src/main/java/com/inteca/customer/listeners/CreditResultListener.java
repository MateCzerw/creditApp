package com.inteca.customer.listeners;



import com.inteca.customer.config.JmsConfig;
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
public class CreditResultListener {


    @JmsListener(destination = JmsConfig.CREATE_CREDIT)
    public void listen(Test test) {

        System.out.println(test);

    }
}
