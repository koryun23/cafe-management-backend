package com.cafe.mapper.product;

import com.cafe.dto.ProductUpdateRequestDto;
import com.cafe.service.core.product.ProductUpdateParams;
import org.springframework.stereotype.Component;

@Component
public class ProductUpdateParamsMapperImpl implements ProductUpdateParamsMapper {
    @Override
    public ProductUpdateParams apply(ProductUpdateRequestDto dto) {
        return new ProductUpdateParams(
                dto.getId(),
                dto.getName(),
                dto.getAmount(),
                dto.getPrice()
        );
    }
}
