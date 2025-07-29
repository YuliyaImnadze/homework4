package org.example.mapper;

import org.example.dto.request.ProductDtoRequest;
import org.example.dto.response.ProductDtoResponse;
import org.example.entity.ProductEntity;
import org.example.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    ProductEntity toProductEntity(ProductDtoRequest dtoRequest, UserEntity user);

    ProductDtoResponse toProductDtoResponse(ProductEntity productEntity);
}
