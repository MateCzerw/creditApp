package com.inteca.customer.services;

import com.inteca.customer.domain.Customer;
import com.inteca.customer.repositiories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import model.CustomerDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void saveCustomer(CustomerDto customerDto) {

        mapAndSave(customerDto);


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
