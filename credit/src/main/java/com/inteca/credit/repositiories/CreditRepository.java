package com.inteca.credit.repositiories;

import com.inteca.credit.domain.Credit;
import org.springframework.data.repository.CrudRepository;

public interface CreditRepository extends CrudRepository<Credit, Integer> {
}
