package com.cafe.service.impl.product;

import com.cafe.entity.product.Product;
import com.cafe.entity.product.ProductInOrder;
import com.cafe.entity.product.ProductInOrderStatusType;
import com.cafe.repository.ProductInOrderRepository;
import com.cafe.service.core.order.OrderService;
import com.cafe.service.core.product.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;


@Service
public class ProductInOrderServiceImpl implements ProductInOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductInOrderServiceImpl.class);
    private final ProductInOrderRepository productInOrderRepository;
    private final ProductService productService;
    private final OrderService orderService;

    public ProductInOrderServiceImpl(ProductInOrderRepository productInOrderRepository, ProductService productService, OrderService orderService) {
        this.productInOrderRepository = productInOrderRepository;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Transactional
    @Override
    public ProductInOrder create(ProductInOrderCreationParams params) {
        Assert.notNull(params, "Product in order creation params should not be null");
        LOGGER.info("Creating a new product in order according to the Product in order creation params - {}", params);
        ProductInOrder productInOrder = productInOrderRepository.save(new ProductInOrder(
                productService.findByName(params.getProductName()).orElseThrow(() -> new ProductNotFoundException(params.getProductName())),
                orderService.getById(params.getOrderId()),
                params.getAmount()
        ));
        LOGGER.info("Successfully created a new product according to the product creation params - {}, result - {}", params, productInOrder);
        return productInOrder;
    }

    @Transactional
    @Override
    public ProductInOrder update(ProductInOrderUpdateParams params) {
        Assert.notNull(params, "Product in order update params should not be null");
        LOGGER.info("Updating a product in order according to the product in order update params - {}", params);
        ProductInOrder productInOrder = new ProductInOrder(
                productService.getByName(params.getProductName()),
                orderService.getById(params.getOrderId()),
                params.getAmount()
        );
        productInOrder.setId(params.getId());
        productInOrder.setProductInOrderStatusType(params.getStatus());
        ProductInOrder savedProductInOrder = productInOrderRepository.save(productInOrder);
        LOGGER.info("Successfully updated a product in order according to the product in order update params - {}, result - {}", params, savedProductInOrder);
        return savedProductInOrder;
    }

    @Transactional
    @Override
    public void markAllAs(Long orderId, ProductInOrderStatusType status) {
        Assert.notNull(orderId, "order id should not be null");
        Assert.notNull(status, "Product in order status type should not be null");
        LOGGER.info("Marking all products in order having an id of {} to {}", orderId, status);
        List<ProductInOrder> productInOrderList = productInOrderRepository.findAll();
        for(ProductInOrder productInOrder : productInOrderList) {
            update(new ProductInOrderUpdateParams(
                    productInOrder.getId(),
                    productInOrder.getProduct().getProductName(),
                    productInOrder.getOrder().getId(),
                    productInOrder.getAmount(),
                    status
            ));
            if(status == ProductInOrderStatusType.CANCELLED) {
                Product product = productInOrder.getProduct();
                productService.updateProduct(new ProductUpdateParams(
                        product.getProductName(),
                        product.getProductName(),
                        product.getAmount() + productInOrder.getAmount(),
                        product.getPrice()
                ));
            }
        }
    }
}
