package com.inteca.customer.services;

import com.inteca.customer.config.JmsConfig;
import com.inteca.customer.domain.Customer;
import com.inteca.customer.domain.CustomerNotFoundException;
import com.inteca.customer.repositiories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import model.CustomerDto;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final JmsTemplate jmsTemplate;

    public void saveCustomer(CustomerDto customerDto) {

        mapAndSave(customerDto);


    }

    public void getCustomers(List<Integer> creditNumbers) {

        List<CustomerDto> customers = creditNumbers.stream()
                .map(creditNumber -> customerRepository.findByCreditId(creditNumber))
                .map(customer -> customer.orElseThrow(() -> new CustomerNotFoundException()))
                .map(customer -> toDto(customer))
                .collect(Collectors.toList());


        jmsTemplate.convertAndSend(JmsConfig.SEND_CUSTOMERS, customers);
    }

    private CustomerDto toDto(Customer customer) {

        CustomerDto dto = new CustomerDto();
        dto.setPesel(customer.getPesel());
        dto.setFirstName(customer.getFirstName());
        dto.setSurname(customer.getSurname());
        dto.setCreditId(customer.getCreditId());

        return dto;
    }

    private void mapAndSave(CustomerDto customerDto) {

        Customer customer = Customer.builder()
                .firstName(customerDto.getFirstName())
                .surname(customerDto.getSurname())
                .pesel(customerDto.getPesel())
                .creditId(customerDto.getCreditId())
                .build();

        customerRepository.save(customer);
    }


}
