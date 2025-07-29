package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.ProductDtoRequest;
import org.example.dto.response.ProductDtoResponse;
import org.example.entity.ProductEntity;
import org.example.entity.UserEntity;
import org.example.exception.ProductNotFoundException;
import org.example.exception.UserNotFoundException;
import org.example.mapper.ProductMapper;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final ProductMapper productMapper;

    public ProductDtoResponse getProductById(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.toProductDtoResponse(product);
    }

    public List<ProductDtoResponse> getProductsByUserId(Long userId) {
        return productRepository.findByUserId(userId).stream()
                .map(productMapper::toProductDtoResponse)
                .toList();
    }

    public ProductDtoResponse addProductToUser(ProductDtoRequest request) {
        UserEntity user = userService.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));
        ProductEntity product = productMapper.toProductEntity(request, user);
        return productMapper.toProductDtoResponse(productRepository.save(product));
    }
}
