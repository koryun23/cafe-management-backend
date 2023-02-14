package com.cafe.service.core.product;

import com.cafe.entity.product.ProductInOrder;
import com.cafe.entity.product.ProductInOrderStatusType;

import java.util.List;

public interface ProductInOrderService {

    ProductInOrder create(ProductInOrderCreationParams params);

    ProductInOrder update(ProductInOrderUpdateParams params);

    void markAllAs(Long orderId, ProductInOrderStatusType status);

    List<ProductInOrder> findAllByOrderId(Long orderId);

    boolean existsByProductId(Long productId);
}
