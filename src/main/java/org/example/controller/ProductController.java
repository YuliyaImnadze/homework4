package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.ProductDtoRequest;
import org.example.dto.response.ProductDtoResponse;
import org.example.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductDtoResponse>> getProductsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(productService.getProductsByUserId(userId));
    }

    @PostMapping()
    public ResponseEntity<ProductDtoResponse> addProductToUser(@RequestBody ProductDtoRequest request) {
        return ResponseEntity.ok(productService.addProductToUser(request));
    }
}
