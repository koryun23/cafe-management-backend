package com.cafe.facade.core.product;

import com.cafe.dto.*;

public interface ProductFacade {
    ProductRegistrationResponseDto registerProduct(ProductRegistrationRequestDto dto);

    ProductInOrderRegistrationResponseDto registerProductInOrder(ProductInOrderRegistrationRequestDto dto);

    ProductInOrderUpdateResponseDto updateProductInOrder(ProductInOrderUpdateRequestDto dto);

    ProductUpdateResponseDto updateProduct(ProductUpdateRequestDto dto);
}
