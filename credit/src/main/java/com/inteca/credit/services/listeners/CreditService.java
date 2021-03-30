package com.inteca.credit.services.listeners;

import com.inteca.credit.config.JmsConfig;
import com.inteca.credit.domain.Credit;
import com.inteca.credit.repositiories.CreditRepository;
import model.CreditDto;
import model.CreditOrder;
import lombok.RequiredArgsConstructor;
import model.CustomerDto;
import model.ProductDto;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final JmsTemplate jmsTemplate;
    private final CreditRepository creditRepository;

    @Transactional
    public int newCreditOrder(CreditOrder creditOrder) {


        Credit saveCredit = mapAndSave(creditOrder.getCreditDto());
        //save credit

        CustomerDto customerDto = creditOrder.getCustomerDto();
        customerDto.setCreditId(saveCredit.getId());
        ProductDto productDto = creditOrder.getProductDto();
        productDto.setCreditId(saveCredit.getId());

        jmsTemplate.convertAndSend(JmsConfig.SAVE_CUSTOMER, customerDto);
        jmsTemplate.convertAndSend(JmsConfig.SAVE_PRODUCT, productDto);


        return saveCredit.getId();
    }

    private Credit mapAndSave(CreditDto creditDto) {

        Credit credit = Credit.builder()
                .creditName(creditDto.getCreditName())
                .build();

        return creditRepository.save(credit);

    }


}
