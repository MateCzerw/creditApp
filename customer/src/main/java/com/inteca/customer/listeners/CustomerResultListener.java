package com.inteca.customer.listeners;



import com.inteca.customer.config.JmsConfig;
import com.inteca.customer.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.CustomerDto;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jt on 2019-09-09.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class CustomerResultListener {

    private final CustomerService customerService;

    @JmsListener(destination = JmsConfig.SAVE_CUSTOMER)
    public void listen(CustomerDto customerDto) {

        customerService.saveCustomer(customerDto);

    }

    @JmsListener(destination = JmsConfig.GET_CUSTOMERS)
    public void listen(List<Integer> creditNumbers) {

        customerService.getCustomers(creditNumbers);
    }
}
