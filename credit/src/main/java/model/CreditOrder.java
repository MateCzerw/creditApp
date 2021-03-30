package model;


import lombok.*;
import model.CreditDto;
import model.CustomerDto;
import model.ProductDto;


@Getter
@Setter
public class CreditOrder {

   private CreditDto creditDto;
   private ProductDto productDto;
   private CustomerDto customerDto;


}
