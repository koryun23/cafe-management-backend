package com.cafe.facade.impl.product;

import com.cafe.dto.request.*;
import com.cafe.dto.response.*;
import com.cafe.dto.response.error.*;
import com.cafe.entity.order.Order;
import com.cafe.entity.order.OrderStatusType;
import com.cafe.entity.product.Product;
import com.cafe.entity.product.ProductInOrder;
import com.cafe.entity.product.ProductInOrderStatusType;
import com.cafe.facade.core.product.ProductFacade;
import com.cafe.mapper.product.*;
import com.cafe.service.core.order.OrderService;
import com.cafe.service.core.product.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final ProductInOrderUpdateParamsMapper productInOrderUpdateParamsMapper;
    private final ProductInOrderUpdateResponseDtoMapper productInOrderUpdateResponseDtoMapper;

    public ProductFacadeImpl(ProductService productService,
                             ProductInOrderService productInOrderService,
                             OrderService orderService,
                             ProductCreationParamsMapper productCreationParamsMapper,
                             ProductRegistrationResponseDtoMapper productRegistrationResponseDtoMapper,
                             ProductUpdateParamsMapper productUpdateParamsMapper,
                             ProductUpdateResponseDtoMapper productUpdateResponseDtoMapper,
                             ProductInOrderCreationParamsMapper productInOrderCreationParamsMapper,
                             ProductInOrderRegistrationResponseDtoMapper productInOrderRegistrationResponseDtoMapper,
                             ProductInOrderUpdateParamsMapper productInOrderUpdateParamsMapper,
                             ProductInOrderUpdateResponseDtoMapper productInOrderUpdateResponseDtoMapper) {
        Assert.notNull(productService, "Product service should not be null");
        Assert.notNull(productInOrderService, "Product in order service should not be null");
        Assert.notNull(orderService, "Order service should not be null");
        Assert.notNull(productCreationParamsMapper, "Product registration request dto mapper should not be null");
        Assert.notNull(productRegistrationResponseDtoMapper, "Product registration response dto mapper should not be null");
        Assert.notNull(productUpdateParamsMapper, "Product update params mapper should not be null");
        Assert.notNull(productUpdateResponseDtoMapper, "Product update response dto mapper should not be null");
        Assert.notNull(productInOrderCreationParamsMapper, "Product in order creation params mapper should not be null");
        Assert.notNull(productInOrderRegistrationResponseDtoMapper, "Product in order registration response dto mapper should not be null");
        Assert.notNull(productInOrderUpdateParamsMapper, "Product in order update params mapper should not be null");
        Assert.notNull(productInOrderUpdateResponseDtoMapper, "Product in order update response dto mapper should not be null");
        this.productService = productService;
        this.productInOrderService = productInOrderService;
        this.orderService = orderService;
        this.productCreationParamsMapper = productCreationParamsMapper;
        this.productRegistrationResponseDtoMapper = productRegistrationResponseDtoMapper;
        this.productUpdateParamsMapper = productUpdateParamsMapper;
        this.productUpdateResponseDtoMapper = productUpdateResponseDtoMapper;
        this.productInOrderCreationParamsMapper = productInOrderCreationParamsMapper;
        this.productInOrderRegistrationResponseDtoMapper = productInOrderRegistrationResponseDtoMapper;
        this.productInOrderUpdateParamsMapper = productInOrderUpdateParamsMapper;
        this.productInOrderUpdateResponseDtoMapper = productInOrderUpdateResponseDtoMapper;
    }

    @Override
    public ProductRegistrationResponseDto registerProduct(ProductRegistrationRequestDto dto) {
        Assert.notNull(dto, "Product registration request dto should not be null");
        LOGGER.info("Registering a new product according to the product registration request dto - {}", dto);
        if(productService.findByName(dto.getName()).isPresent()) {
            return new ErrorProductRegistrationResponseDto(
                    List.of(String.format("Product with the name %s is already registered", dto.getName())),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        Product product = productService.create(productCreationParamsMapper.apply(dto));
        ProductRegistrationResponseDto responseDto = productRegistrationResponseDtoMapper.apply(product);
        LOGGER.info("Successfully registered a new product according to the product registration request dto - {}, response - {}", dto, responseDto);
        return responseDto;
    }

    @Override
    public ProductUpdateResponseDto updateProduct(ProductUpdateRequestDto dto) {
        Assert.notNull(dto, "Product update request dto should not be null");
        LOGGER.info("Updating a product according to the product update request dto - {}", dto);
        if(productService.findById(dto.getId()).isEmpty()) {
            return new ErrorProductUpdateResponseDto(
                    List.of(String.format("Cannot update product with an id of %d because it does not exist.", dto.getId())),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        Optional<Product> productOptional = productService.findByName(dto.getName());
        if(productOptional.isPresent() && (!productOptional.get().getId().equals(dto.getId()))) {
            return new ErrorProductUpdateResponseDto(
                    List.of(String.format("The product named %s already exists.", dto.getName())),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        Product product = productService.updateProduct(productUpdateParamsMapper.apply(dto));
        ProductUpdateResponseDto responseDto = productUpdateResponseDtoMapper.apply(product);
        LOGGER.info("Successfully updated a product according to the product update request dto - {}, response - {}", dto, responseDto);
        return responseDto;
    }

    @Override
    public AllProductsRetrievalResponseDto fetchAll() {
        LOGGER.info("Retrieving all product dtos");
        List<ProductRetrievalResponseDto> productRetrievalResponseDtoList = productService.getAll().stream()
                .map(product -> new ProductRetrievalResponseDto(product.getId(), product.getName(), product.getAmount(), product.getPrice(), HttpStatus.OK))
                .collect(Collectors.toList());
        AllProductsRetrievalResponseDto result = new AllProductsRetrievalResponseDto(productRetrievalResponseDtoList, HttpStatus.OK);
        LOGGER.info("Successfully retrieved all product dtos, result - {}", result);
        return result;
    }

    @Override
    public ProductDeletionResponseDto deleteProduct(ProductDeletionRequestDto dto) {
        Assert.notNull(dto, "Product deletion request dto should not be null");
        LOGGER.info("Deleting a product according to the product deletion request dto - {}", dto);
        Long productId = dto.getProductId();
        if(productService.findById(productId).isEmpty()) {
            return new ProductDeletionResponseDto(HttpStatus.NOT_ACCEPTABLE, List.of(String.format("Cannot delete a product having an id of %d because it does not exist", productId)));
        }

        if(productInOrderService.existsByProductId(dto.getProductId())) {
            return new ProductDeletionResponseDto(
                    HttpStatus.NOT_ACCEPTABLE,
                    List.of(String.format("Cannot delete the product with an id of %d at this moment because it is ordered", dto.getProductId()))
            );
        }

        productService.deleteProduct(dto.getProductId());
        LOGGER.info("Successfully deleted a product according to the product deletion request dto - {}", dto);
        return new ProductDeletionResponseDto(HttpStatus.OK);
    }

    @Override
    public ProductInOrderListRetrievalResponseDto getAllProductsInOrderByOrderId(ProductInOrderListRetrievalRequestDto dto) {
        Assert.notNull(dto, "Product in order list retrieval request dto should not be null");
        LOGGER.info("Retrieving a list of all products in order according to the product in order list retrieval request dto - {}", dto);
        Optional<Order> orderOptional = orderService.findById(dto.getOrderId());
        if(orderOptional.isEmpty()) {
            return new ErrorProductInOrderListRetrievalResponseDto(
                    List.of(String.format("Order with an id of %d does not exist.", dto.getOrderId())),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        Order order = orderOptional.get();
        if(!order.getWaiter().getUsername().equals(dto.getWaiterUsername())) {
            return new ErrorProductInOrderListRetrievalResponseDto(
                    List.of(String.format("User %s cannot retrieve products in order created by the user %s", dto.getWaiterUsername(), order.getWaiter().getUsername())),
                    HttpStatus.OK
            );
        }
        List<ProductInOrderRetrievalResponseDto> allByOrderId = productInOrderService.findAllByOrderId(dto.getOrderId()).stream()
                .map(productInOrder -> new ProductInOrderRetrievalResponseDto(
                        productInOrder.getId(),
                        productInOrder.getProduct().getName(),
                        productInOrder.getAmount(),
                        productInOrder.getProductInOrderStatusType(),
                        productInOrder.getRegisteredAt(),
                        HttpStatus.OK
                )).collect(Collectors.toList());
        ProductInOrderListRetrievalResponseDto result = new ProductInOrderListRetrievalResponseDto(allByOrderId, HttpStatus.OK);
        LOGGER.info("Successfully retrieved a list of all products in order according to the product in order list retrieval request dto - {}, result - {}", dto, result);
        return result;
    }

    @Override
    public ProductInOrderRegistrationResponseDto registerProductInOrder(ProductInOrderRegistrationRequestDto dto) {
        Assert.notNull(dto, "Product in order registration request dto should not be null");
        LOGGER.info("Registering a new product according to the product in order registration request dto - {}", dto);
        Optional<Product> productOptional = productService.findByName(dto.getProductName());
        if(productOptional.isEmpty()) {
            return new ErrorProductInOrderRegistrationResponseDto(
                    List.of(String.format("No product named as: %s", dto.getProductName())),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        Product product = productOptional.get();
        Integer productAmount = product.getAmount();
        Integer productInOrderAmount = dto.getAmount();
        if(productAmount < productInOrderAmount) {
            return new ErrorProductInOrderRegistrationResponseDto(
                    List.of(String.format("Could not register a product in order because its amount(%d) exceeds the amount of the product(%d)", productInOrderAmount, productAmount)),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        Optional<Order> orderOptional = orderService.findById(dto.getOrderId());
        if(orderOptional.isEmpty()) {
            return new ErrorProductInOrderRegistrationResponseDto(
                    List.of(String.format("Could not register a product in order for an order with an id of %d because the order does not exist.", dto.getOrderId())),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        Order order = orderOptional.get();
        if(!order.getWaiter().getUsername().equals(dto.getWaiterUsername())) {
            return new ErrorProductInOrderRegistrationResponseDto(
                    List.of("Could not register a product in order since the order belongs to another user."),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        ProductInOrder productInOrder = productInOrderService.create(productInOrderCreationParamsMapper.apply(dto));
        productService.updateProduct(new ProductUpdateParams(
                product.getId(),
                product.getName(),
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
            return new ErrorProductInOrderUpdateResponseDto(
                    List.of(String.format("The amount must be a positive number, actual amount - %d", dto.getAmount())),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        if(productService.findByName(dto.getProductName()).isEmpty()) {
            return new ErrorProductInOrderUpdateResponseDto(
                    List.of(String.format("No product found named as %s", dto.getProductName())),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        Optional<Order> orderOptional = orderService.findById(dto.getOrderId());
        if(orderOptional.isEmpty()) {
            return new ErrorProductInOrderUpdateResponseDto(
                    List.of(String.format("No order found having an id of %d", dto.getOrderId())),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        Order order = orderOptional.get();
        if(order.getOrderStatusType() != OrderStatusType.OPEN) {
            return new ErrorProductInOrderUpdateResponseDto(
                    List.of(String.format("The order having an id of %d is not open, therefore cannot have a product in order attached to it", dto.getOrderId())),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        ProductInOrder productInOrder = productInOrderService.update(productInOrderUpdateParamsMapper.apply(dto));
        if(productInOrder.getProductInOrderStatusType() == ProductInOrderStatusType.CANCELLED) {
            Product product = productInOrder.getProduct();

            productService.updateProduct(new ProductUpdateParams(
                    product.getId(),
                    product.getName(),
                    product.getAmount() + dto.getAmount(), // dto.getAmount()????
                    product.getPrice()
            ));
        }
        ProductInOrderUpdateResponseDto responseDto = productInOrderUpdateResponseDtoMapper.apply(productInOrder);
        LOGGER.info("Successfully updated product in order according to the request dto - {}, response - {}", dto, responseDto);
        return responseDto;
    }
}