package com.cafe.mapper.product;

import com.cafe.dto.ProductInOrderRegistrationRequestDto;
import com.cafe.service.core.product.ProductInOrderCreationParams;
import org.springframework.stereotype.Component;

import java.util.function.Function;

public interface ProductInOrderCreationParamsMapper extends Function<ProductInOrderRegistrationRequestDto, ProductInOrderCreationParams> {

    ProductInOrderCreationParams apply(ProductInOrderRegistrationRequestDto dto);
}
