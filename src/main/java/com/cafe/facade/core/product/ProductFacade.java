package com.cafe.facade.core.product;

import com.cafe.dto.request.*;
import com.cafe.dto.response.*;

public interface ProductFacade {
    ProductRegistrationResponseDto registerProduct(ProductRegistrationRequestDto dto);

    ProductInOrderRegistrationResponseDto registerProductInOrder(ProductInOrderRegistrationRequestDto dto);

    ProductInOrderUpdateResponseDto updateProductInOrder(ProductInOrderUpdateRequestDto dto);

    ProductUpdateResponseDto updateProduct(ProductUpdateRequestDto dto);

    AllProductsRetrievalResponseDto fetchAll();

    ProductDeletionResponseDto deleteProduct(ProductDeletionRequestDto dto);

    ProductInOrderListRetrievalResponseDto getAllProductsInOrderByOrderId(ProductInOrderListRetrievalRequestDto dto);
}
