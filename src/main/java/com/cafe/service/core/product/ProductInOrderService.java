package com.cafe.service.core.product;

import com.cafe.entity.product.ProductInOrder;

public interface ProductInOrderService {

    ProductInOrder create(ProductInOrderCreationParams params);

    ProductInOrder update(ProductInOrderUpdateParams params);
}
