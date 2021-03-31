package com.inteca.product.repositiories;


import com.inteca.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByCreditId(Integer creditId);
}
