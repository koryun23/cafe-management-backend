package com.cafe.mapper.product;

import com.cafe.dto.ProductUpdateResponseDto;
import com.cafe.entity.product.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductUpdateResponseDtoMapperImpl implements ProductUpdateResponseDtoMapper {

    @Override
    public ProductUpdateResponseDto apply(Product product) {
        return new ProductUpdateResponseDto(
                product.getProductName(),
                product.getPrice(),
                product.getAmount(),
                LocalDateTime.now()
        );
    }
}
