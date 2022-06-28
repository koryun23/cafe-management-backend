package com.cafe.mapper.product;

import com.cafe.dto.ProductInOrderRegistrationRequestDto;
import com.cafe.service.core.product.ProductInOrderCreationParams;
import org.springframework.stereotype.Component;

@Component
public class ProductInOrderCreationParamsMapperImpl implements ProductInOrderCreationParamsMapper {
    @Override
    public ProductInOrderCreationParams apply(ProductInOrderRegistrationRequestDto dto) {
        return new ProductInOrderCreationParams(
                dto.getProductName(),
                dto.getOrderId(),
                dto.getAmount()
        );
    }
}
