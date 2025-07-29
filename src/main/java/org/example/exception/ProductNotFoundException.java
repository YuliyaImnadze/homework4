package org.example.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Product with id = %d not found".formatted(id));
    }
}
