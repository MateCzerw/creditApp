package com.inteca.product.services;


import com.inteca.product.config.JmsConfig;
import com.inteca.product.domain.Product;
import com.inteca.product.domain.ProductNotFoundException;
import com.inteca.product.repositiories.ProductRepository;
import lombok.RequiredArgsConstructor;
import model.ProductDto;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final JmsTemplate jmsTemplate;

    public void saveProduct(ProductDto productDto) {

        mapAndSave(productDto);
    }

    public void getProducts(List<Integer> creditNumbers) {

        List<ProductDto> productDtos = creditNumbers.stream()
                .map(creditNumber -> productRepository.findByCreditId(creditNumber))
                .map(product -> product.orElseThrow(() -> new ProductNotFoundException()))
                .map(product -> toDto(product))
                .collect(Collectors.toList());

        jmsTemplate.convertAndSend(JmsConfig.SEND_PRODUCTS, productDtos);
    }

    private ProductDto toDto(Product product) {

        ProductDto dto = new ProductDto();
        dto.setCreditId(product.getCreditId());
        dto.setProductName(product.getProductName());
        dto.setValue(product.getValue());

        return dto;
    }

    private void mapAndSave(ProductDto productDto) {

        Product product = Product.builder()
                .productName(productDto.getProductName())
                .value(productDto.getValue())
                .creditId(productDto.getCreditId())
                .build();

        productRepository.save(product);
    }



}
