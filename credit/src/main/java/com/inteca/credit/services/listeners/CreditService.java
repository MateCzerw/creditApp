package com.inteca.credit.services.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.inteca.credit.config.JmsConfig;
import com.inteca.credit.domain.Credit;
import com.inteca.credit.repositiories.CreditRepository;
import model.*;
import lombok.RequiredArgsConstructor;
import model.response.CreditResponse;
import model.response.CreditResponseDto;
import model.response.CustomerResponse;
import model.response.ProductResponse;
import org.springframework.jms.core.JmsTemplate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CreditService {


    private final Gson gson;
    private final ObjectMapper objectMapper;
    private final JmsTemplate jmsTemplate;
    private final CreditRepository creditRepository;

    @Transactional
    public int createCredit(CreditOrder creditOrder) {

        Credit saveCredit = mapAndSave(creditOrder.getCreditDto());

        createProduct(creditOrder, saveCredit);

        createCustomer(creditOrder, saveCredit);

        return saveCredit.getId();
    }

    private void createCustomer(CreditOrder creditOrder, Credit saveCredit) {
        CustomerDto customerDto = creditOrder.getCustomerDto();
        customerDto.setCreditId(saveCredit.getId());
        jmsTemplate.convertAndSend(JmsConfig.SAVE_CUSTOMER, customerDto);
    }

    private void createProduct(CreditOrder creditOrder, Credit saveCredit) {
        ProductDto productDto = creditOrder.getProductDto();
        productDto.setCreditId(saveCredit.getId());
        jmsTemplate.convertAndSend(JmsConfig.SAVE_PRODUCT, productDto);
    }

    private Credit mapAndSave(CreditDto creditDto) {

        Credit credit = Credit.builder()
                .creditName(creditDto.getCreditName())
                .build();

        return creditRepository.save(credit);

    }


    public List<CreditResponseDto> getCredits()  {


        Iterable<Credit> credits = creditRepository.findAll();

        List<Integer> creditNumbers = StreamSupport
                .stream(credits.spliterator(), false)
                .map(Credit::getId)
                .collect(Collectors.toList());


        Map<Integer, CustomerDto> customersMap = new HashMap<>();

        try {
            jmsTemplate.convertAndSend(JmsConfig.GET_CUSTOMERS, creditNumbers);
            Object customersObject = jmsTemplate.receiveAndConvert(JmsConfig.SEND_CUSTOMERS);
            String customersJson = gson.toJson(customersObject);
            List<CustomerDto> customers = Arrays.asList(objectMapper.readValue(customersJson, CustomerDto[].class));
            customers.stream().forEach(customer -> customersMap.put(customer.getCreditId(), customer));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        Map<Integer, ProductDto> productsMap = new HashMap<>();

        try {
            jmsTemplate.convertAndSend(JmsConfig.GET_PRODUCTS, creditNumbers);
            Object productsObject = jmsTemplate.receiveAndConvert(JmsConfig.SEND_PRODUCTS);
            String productsJson = gson.toJson(productsObject);
            List<ProductDto> products = Arrays.asList(objectMapper.readValue(productsJson, ProductDto[].class));
            products.stream().forEach(product -> productsMap.put(product.getCreditId(), product));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }



        return aggregateCredits(credits, customersMap, productsMap);
    }

    private List<CreditResponseDto> aggregateCredits(Iterable<Credit> credits, Map<Integer, CustomerDto> customersMap, Map<Integer, ProductDto> productsMap) {

        List<CreditResponseDto> creditDtos = StreamSupport.stream(credits.spliterator(), false)
                .map(credit -> getCreditResponseDto(customersMap, productsMap, credit))
                .collect(Collectors.toList());

        return creditDtos;
    }

    private CreditResponseDto getCreditResponseDto(Map<Integer, CustomerDto> customersMap, Map<Integer, ProductDto> productsMap, Credit credit) {
        CreditResponse creditResponse = mapToCreditResponse(credit);

        CustomerDto customerDto = customersMap.get(credit.getId());
        CustomerResponse customerResponse = mapToCustomerResponse(customerDto);

        ProductDto productDto = productsMap.get(credit.getId());
        ProductResponse productResponse = mapToProductResponse(productDto);


        CreditResponseDto dto = new CreditResponseDto();
        dto.setCredit(creditResponse);
        dto.setCustomer(customerResponse);
        dto.setProduct(productResponse);

        return dto;
    }

    private ProductResponse mapToProductResponse(ProductDto productDto) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductName(productDto.getProductName());
        productResponse.setValue(productDto.getValue());
        return productResponse;
    }

    private CustomerResponse mapToCustomerResponse(CustomerDto customerDto) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setPesel(customerDto.getPesel());
        customerResponse.setFirstName(customerDto.getFirstName());
        customerResponse.setSurname(customerDto.getSurname());
        return customerResponse;
    }

    private CreditResponse mapToCreditResponse(Credit credit) {
        CreditResponse creditResponse = new CreditResponse();
        creditResponse.setCreditName(credit.getCreditName());
        return creditResponse;
    }





}
