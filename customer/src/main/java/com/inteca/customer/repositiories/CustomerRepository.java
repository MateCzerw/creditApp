package com.inteca.customer.repositiories;

import com.inteca.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

        Optional<Customer> findByCreditId(Integer creditId);

}
