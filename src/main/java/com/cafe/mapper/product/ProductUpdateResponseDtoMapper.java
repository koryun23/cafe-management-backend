package com.cafe.mapper.product;

import com.cafe.dto.ProductUpdateResponseDto;
import com.cafe.entity.product.Product;

import java.util.function.Function;

public interface ProductUpdateResponseDtoMapper extends Function<Product, ProductUpdateResponseDto> {

    ProductUpdateResponseDto apply(Product product);
}
