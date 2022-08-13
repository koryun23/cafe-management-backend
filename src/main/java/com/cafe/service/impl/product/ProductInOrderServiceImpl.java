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
        Assert.notNull(productInOrderRepository, "product in order repository should not be null");
        Assert.notNull(productService, "product service should not be null");
        Assert.notNull(orderService, "order service should not be null");
        this.productInOrderRepository = productInOrderRepository;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Transactional
    @Override
    public ProductInOrder create(ProductInOrderCreationParams params) {
        Assert.notNull(params, "Product in order creation params should not be null");
        LOGGER.info("Creating a new product in order according to the Product in order creation params - {}", params);
        ProductInOrder productInOrder = new ProductInOrder(
                productService.findByName(params.getProductName()).orElseThrow(() -> new ProductNotFoundException(params.getProductName())),
                orderService.getById(params.getOrderId()),
                params.getAmount(),
                params.getCreatedAt()
        );
        productInOrder.setProductInOrderStatusType(ProductInOrderStatusType.ACTIVE);
        ProductInOrder savedProductInOrder = productInOrderRepository.save(productInOrder);
        LOGGER.info("Successfully created a new product according to the product creation params - {}, result - {}", params, productInOrder);
        return savedProductInOrder;
    } // tested

    @Transactional
    @Override
    public ProductInOrder update(ProductInOrderUpdateParams params) {
        Assert.notNull(params, "Product in order update params should not be null");
        LOGGER.info("Updating a product in order according to the product in order update params - {}", params);
        ProductInOrder originalProductInOrder = productInOrderRepository.findById(params.getId()).orElseThrow(() -> new ProductInOrderNotFoundException(params.getId()));
        ProductInOrder productInOrder = new ProductInOrder(
                productService.getByName(params.getProductName()),
                orderService.getById(params.getOrderId()),
                params.getAmount(),
                originalProductInOrder.getRegisteredAt()
        );
        productInOrder.setId(params.getId());
        productInOrder.setProductInOrderStatusType(params.getStatus());
        ProductInOrder savedProductInOrder = productInOrderRepository.save(productInOrder);

        LOGGER.info("Successfully updated a product in order according to the product in order update params - {}, result - {}", params, savedProductInOrder);
        return savedProductInOrder;
    } // tested

    @Transactional
    @Override
    public void markAllAs(Long orderId, ProductInOrderStatusType status) {
        Assert.notNull(orderId, "order id should not be null");
        Assert.notNull(status, "Product in order status type should not be null");
        LOGGER.info("Marking all products in order having an id of {} to {}", orderId, status);
        List<ProductInOrder> productInOrderList = productInOrderRepository.findAllByOrderId(orderId);
        for(ProductInOrder productInOrder : productInOrderList) {
            if(productInOrder.getProductInOrderStatusType() != ProductInOrderStatusType.ACTIVE) {
                continue;
            }
            update(new ProductInOrderUpdateParams(
                    productInOrder.getId(),
                    productInOrder.getProduct().getName(),
                    productInOrder.getOrder().getId(),
                    productInOrder.getAmount(),
                    status
            ));
            if(status == ProductInOrderStatusType.CANCELLED) {
                Product product = productInOrder.getProduct();
                productService.updateProduct(new ProductUpdateParams(
                        product.getId(),
                        product.getName(),
                        product.getAmount() + productInOrder.getAmount(),
                        product.getPrice()
                ));
            }
        }
    }

    @Override
    public List<ProductInOrder> findAllByOrderId(Long orderId) {
        Assert.notNull(orderId, "Order id should not be null");
        LOGGER.info("Retrieving a list of products in the order with an id of {}", orderId);
        List<ProductInOrder> allByOrderId = productInOrderRepository.findAllByOrderId(orderId);
        LOGGER.info("Successfully retrieved the list of products in the order with an id of {}, result - {}",orderId, allByOrderId);
        return allByOrderId;
    }

    @Override
    public boolean existsByProductId(Long productId) {
        Assert.notNull(productId, "Product id should not be null");
        LOGGER.info("Checking if a product with an id of {} is in an order", productId);
        boolean result = productInOrderRepository.existsByProductId(productId);
        LOGGER.info("Checked if a product with an id of {} is in an order, result - {}", productId, result);
        return result;
    }
}
