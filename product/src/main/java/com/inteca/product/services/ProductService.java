package com.inteca.product.services;


import com.inteca.product.domain.Product;
import com.inteca.product.repositiories.ProductRepository;
import lombok.RequiredArgsConstructor;
import model.ProductDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void saveProduct(ProductDto productDto) {

        mapAndSave(productDto);


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
