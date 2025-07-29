package org.example.dto.response;

import lombok.*;
import org.example.entity.ProductType;

import java.math.BigDecimal;

@Data
public class ProductDtoResponse {

    private String accountNumber;

    private BigDecimal balance;

    private ProductType productType;

    private UserDtoResponse user;


}
