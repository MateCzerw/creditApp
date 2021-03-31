package com.inteca.product.listeners;



import com.inteca.product.config.JmsConfig;

import com.inteca.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.ProductDto;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jt on 2019-09-09.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ProductResultListener {


    private final ProductService productService;


    @JmsListener(destination = JmsConfig.SAVE_PRODUCT)
    public void listen(ProductDto productDto) {

        productService.saveProduct(productDto);

    }


    @JmsListener(destination = JmsConfig.GET_PRODUCTS)
    public void listen(List<Integer> creditNumbers) {

        productService.getProducts(creditNumbers);
    }
}
