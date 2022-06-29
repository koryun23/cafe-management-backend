package com.cafe.facade.impl.product;

import com.cafe.dto.*;
import com.cafe.entity.order.Order;
import com.cafe.entity.order.OrderStatusType;
import com.cafe.entity.product.Product;
import com.cafe.entity.product.ProductInOrder;
import com.cafe.facade.core.product.ProductFacade;
import com.cafe.mapper.product.*;
import com.cafe.service.core.order.OrderService;
import com.cafe.service.core.product.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ProductFacadeImpl implements ProductFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductFacadeImpl.class);
    private final ProductService productService;
    private final ProductInOrderService productInOrderService;
    private final OrderService orderService;
    private final ProductCreationParamsMapper productCreationParamsMapper;
    private final ProductRegistrationResponseDtoMapper productRegistrationResponseDtoMapper;
    private final ProductUpdateParamsMapper productUpdateParamsMapper;
    private final ProductUpdateResponseDtoMapper productUpdateResponseDtoMapper;
    private final ProductInOrderCreationParamsMapper productInOrderCreationParamsMapper;
    private final ProductInOrderRegistrationResponseDtoMapper productInOrderRegistrationResponseDtoMapper;

    public ProductFacadeImpl(ProductService productService,
                             ProductInOrderService productInOrderService,
                             OrderService orderService,
                             ProductCreationParamsMapper productRegistrationRequestDtoMapper,
                             ProductRegistrationResponseDtoMapper productRegistrationResponseDtoMapper,
                             ProductUpdateParamsMapper productUpdateParamsMapper,
                             ProductUpdateResponseDtoMapper productUpdateResponseDtoMapper,
                             ProductInOrderCreationParamsMapper productInOrderCreationParamsMapper,
                             ProductInOrderRegistrationResponseDtoMapper productInOrderRegistrationResponseDtoMapper) {
        this.productService = productService;
        this.productInOrderService = productInOrderService;
        this.orderService = orderService;
        this.productCreationParamsMapper = productRegistrationRequestDtoMapper;
        this.productRegistrationResponseDtoMapper = productRegistrationResponseDtoMapper;
        this.productUpdateParamsMapper = productUpdateParamsMapper;
        this.productUpdateResponseDtoMapper = productUpdateResponseDtoMapper;
        this.productInOrderCreationParamsMapper = productInOrderCreationParamsMapper;
        this.productInOrderRegistrationResponseDtoMapper = productInOrderRegistrationResponseDtoMapper;
    }

    @Override
    public ProductRegistrationResponseDto registerProduct(ProductRegistrationRequestDto dto) {
        Assert.notNull(dto, "Product registration request dto should not be null");
        LOGGER.info("Registering a new product according to the product registration request dto - {}", dto);
        if(productService.findByName(dto.getName()).isPresent()) {
            return new ProductRegistrationResponseDto(
                List.of(String.format("Product with the name %s is already registered", dto.getName()))
            );
        }
        Product product = productService.create(productCreationParamsMapper.apply(dto));
        ProductRegistrationResponseDto responseDto = productRegistrationResponseDtoMapper.apply(product);
        LOGGER.info("Successfully registered a new product according to the product registration request dto - {}, response - {}", dto, responseDto);
        return responseDto;
    }

    @Override
    public ProductInOrderRegistrationResponseDto registerProductInOrder(ProductInOrderRegistrationRequestDto dto) {
        Assert.notNull(dto, "Product in order registration request dto should not be null");
        LOGGER.info("Registering a new product according to the product in order registration request dto - {}", dto);
        Optional<Product> productOptional = productService.findByName(dto.getProductName());
        if(productOptional.isEmpty()) {
            return new ProductInOrderRegistrationResponseDto(
                List.of(String.format("No product named as: %s", dto.getProductName()))
            );
        }
        Product product = productOptional.get();
        Integer productAmount = product.getAmount();
        Integer productInOrderAmount = dto.getAmount();
        if(productAmount < productInOrderAmount) {
            return new ProductInOrderRegistrationResponseDto(
                    List.of(String.format("Could not register a product in order because its amount(%d) exceeds the amount of the product(%d)", productInOrderAmount, productAmount))
            );
        }
        if(orderService.findById(dto.getOrderId()).isEmpty()) {
            return new ProductInOrderRegistrationResponseDto(
                    List.of(String.format("Could not register a product in order for an order with an id of %d because the order does not exist.", dto.getOrderId()))
            );
        }
        ProductInOrder productInOrder = productInOrderService.create(productInOrderCreationParamsMapper.apply(dto));
        productService.updateProduct(new ProductUpdateParams(
                product.getProductName(),
                product.getProductName(),
                product.getAmount() - dto.getAmount(),
                product.getPrice()
        ));
        ProductInOrderRegistrationResponseDto responseDto = productInOrderRegistrationResponseDtoMapper.apply(productInOrder);
        LOGGER.info("Successfully registered a new product in order according to the product in order registration dto - {}, resopnse - {}",
                dto,
                responseDto
        );
        return responseDto;
    }

    @Override
    public ProductInOrderUpdateResponseDto updateProductInOrder(ProductInOrderUpdateRequestDto dto) {
        Assert.notNull(dto, "Product in order update request dto should not be null");
        LOGGER.info("Updating a product in order according to the product in order update request dto - {}", dto);

        if(dto.getAmount() < 0) {
            return new ProductInOrderUpdateResponseDto(List.of(String.format("The amount must be a positive number, actual amount - %d", dto.getAmount())));
        }
        if(productService.findByName(dto.getProductName()).isEmpty()) {
            return new ProductInOrderUpdateResponseDto(List.of(String.format("No product found named as %s", dto.getProductName())));
        }
        Optional<Order> orderOptional = orderService.findById(dto.getOrderId());
        if(orderOptional.isEmpty()) {
            return new ProductInOrderUpdateResponseDto(List.of(String.format("No order found having an id of %d", dto.getOrderId())));
        }
        Order order = orderOptional.get();
        if(order.getOrderStatusType() != OrderStatusType.OPEN) {
            return new ProductInOrderUpdateResponseDto(List.of(String.format("The order having an id of %d is not open, therefore cannot have a product in order attached to it", dto.getOrderId())));
        }
        ProductInOrder productInOrder = productInOrderService.update(new ProductInOrderUpdateParams(
                dto.getId(),
                dto.getProductName(),
                dto.getOrderId(),
                dto.getAmount(),
                dto.getStatus()
        ));
        ProductInOrderUpdateResponseDto responseDto = new ProductInOrderUpdateResponseDto(
                productInOrder.getId(),
                productInOrder.getProduct().getProductName(),
                productInOrder.getOrder().getId(),
                productInOrder.getAmount(),
                LocalDateTime.now()
        );
        LOGGER.info("Successfully updated product in order according to the request dto - {}, response - {}", dto, responseDto);
        return responseDto;
    }

    @Override
    public ProductUpdateResponseDto updateProduct(ProductUpdateRequestDto dto) {
        Assert.notNull(dto, "Product update request dto should not be null");
        LOGGER.info("Updating a product according to the product update request dto - {}", dto);
        Product product = productService.updateProduct(productUpdateParamsMapper.apply(dto));
        ProductUpdateResponseDto responseDto = productUpdateResponseDtoMapper.apply(product);
        LOGGER.info("Successfully updated a product according to the product update request dto - {}, response - {}", dto, responseDto);
        return responseDto;
    }
}