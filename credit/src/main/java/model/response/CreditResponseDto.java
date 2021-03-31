package model.response;

import lombok.Data;


@Data
public class CreditResponseDto {
    private CreditResponse credit;
    private ProductResponse product;
    private CustomerResponse customer;
}
