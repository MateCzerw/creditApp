package model;

import lombok.Data;

@Data
public class CreditDto {

    private String creditName;

    private CustomerDto customer;

    private ProductDto product;

}
