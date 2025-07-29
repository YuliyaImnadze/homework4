package org.example.dto.request;

import lombok.*;
import org.example.entity.ProductType;

import java.math.BigDecimal;

@Data
public class ProductDtoRequest {

    private String accountNumber;

    private BigDecimal balance = BigDecimal.ZERO;

    private ProductType productType;

    private Long userId;
}
