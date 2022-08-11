package com.cafe.facade.impl.order;

import com.cafe.dto.request.OrderListRetrievalRequestDto;
import com.cafe.dto.request.OrderRegistrationRequestDto;
import com.cafe.dto.response.OrderListRetrievalResponseDto;
import com.cafe.dto.response.OrderRegistrationResponseDto;
import com.cafe.dto.request.OrderUpdateRequestDto;
import com.cafe.dto.response.OrderRetrievalResponseDto;
import com.cafe.dto.response.OrderUpdateResponseDto;
import com.cafe.dto.response.error.ErrorOrderListRetrievalResponseDto;
import com.cafe.dto.response.error.ErrorOrderRegistrationResponseDto;
import com.cafe.dto.response.error.ErrorOrderUpdateResponseDto;
import com.cafe.entity.order.Order;
import com.cafe.entity.order.OrderStatusType;
import com.cafe.entity.product.ProductInOrderStatusType;
import com.cafe.entity.table.CafeTable;
import com.cafe.entity.table.CafeTableAssignedToWaiter;
import com.cafe.entity.table.CafeTableStatusType;
import com.cafe.entity.user.User;
import com.cafe.entity.user.UserRole;
import com.cafe.entity.user.UserRoleType;
import com.cafe.facade.core.order.OrderFacade;
import com.cafe.mapper.order.*;
import com.cafe.service.core.order.OrderService;
import com.cafe.service.core.product.ProductInOrderService;
import com.cafe.service.core.table.CafeTableAssignedToWaiterService;
import com.cafe.service.core.table.CafeTableService;
import com.cafe.service.core.user.UserService;
import com.cafe.service.impl.table.CafeTableNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderFacadeImpl implements OrderFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderFacadeImpl.class);
    private final OrderService orderService;
    private final CafeTableService cafeTableService;
    private final CafeTableAssignedToWaiterService cafeTableAssignedToWaiterService;
    private final UserService userService;
    private final OrderRegistrationResponseDtoMapper orderRegistrationResponseDtoMapper;
    private final OrderUpdateResponseDtoMapper orderUpdateResponseDtoMapper;
    private final OrderUpdateParamsMapepr orderUpdateParamsMapper;
    private final OrderCreationParamsMapper orderRegistrationRequestDtoMapper;
    private final ProductInOrderService productInOrderService;

    public OrderFacadeImpl(OrderService orderService,
                           CafeTableService cafeTableService,
                           CafeTableAssignedToWaiterService cafeTableAssignedToWaiterService,
                           UserService userService,
                           OrderRegistrationResponseDtoMapper orderRegistrationResponseDtoMapper,
                           OrderUpdateResponseDtoMapper orderUpdateResponseDtoMapper,
                           OrderUpdateParamsMapepr orderUpdateParamsMapper,
                           OrderCreationParamsMapper orderRegistrationRequestDtoMapper,
                           ProductInOrderService productInOrderService) {
        Assert.notNull(orderService, "order service should not be null");
        Assert.notNull(cafeTableService, "cafe table service should not be null");
        Assert.notNull(cafeTableAssignedToWaiterService, "cafe table assigned to waiter service should not be null");
        Assert.notNull(orderRegistrationRequestDtoMapper, "order registration request dto mapper should not be null");
        Assert.notNull(orderUpdateResponseDtoMapper, "order update response dto mapper should not be null");
        Assert.notNull(orderUpdateParamsMapper, "order update params mapper should not be null");
        Assert.notNull(orderRegistrationRequestDtoMapper, "order registration request dto mapper should not be null");
        Assert.notNull(productInOrderService, "product in order service should not be null");
        Assert.notNull(userService, "User service should not be null");
        this.orderService = orderService;
        this.cafeTableService = cafeTableService;
        this.cafeTableAssignedToWaiterService = cafeTableAssignedToWaiterService;
        this.userService = userService;
        this.orderRegistrationResponseDtoMapper = orderRegistrationResponseDtoMapper;
        this.orderUpdateResponseDtoMapper = orderUpdateResponseDtoMapper;
        this.orderUpdateParamsMapper = orderUpdateParamsMapper;
        this.orderRegistrationRequestDtoMapper = orderRegistrationRequestDtoMapper;
        this.productInOrderService = productInOrderService;
    }

    @Override
    public OrderRegistrationResponseDto register(OrderRegistrationRequestDto dto) {
        Assert.notNull(dto, "Order registration request should not be null");
        LOGGER.info("Registering a new order according to the order registration request dto - {}", dto);
        Optional<CafeTable> cafeTableOptional = cafeTableService.findById(dto.getCafeTableId());
        if(cafeTableOptional.isEmpty()) {
            return new ErrorOrderRegistrationResponseDto(
                    List.of(String.format("No table found having an id of %d", dto.getCafeTableId())),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        if(!foundTableWithIdAssignedToWaiterWithUsername(dto.getCafeTableId(), dto.getWaiterUsername())) {
            return new ErrorOrderRegistrationResponseDto(
                    List.of(String.format("Table having an id of %d is not assigned to waiter having a username of %s", dto.getCafeTableId(), dto.getWaiterUsername())),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        CafeTable cafeTable = cafeTableOptional.get();
        if(cafeTable.getCafeTableStatusType() != CafeTableStatusType.FREE) {
            return new ErrorOrderRegistrationResponseDto(
                    List.of(String.format("The cafe table with an id of %d is not free, its status is %s", dto.getCafeTableId(), cafeTable.getCafeTableStatusType())),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        Order order = orderService.create(orderRegistrationRequestDtoMapper.apply(dto));
        OrderRegistrationResponseDto orderRegistrationResponseDto = orderRegistrationResponseDtoMapper.apply(order);
        cafeTableService.markAs(order.getTable().getId(), CafeTableStatusType.TAKEN);
        LOGGER.info("Successfully registered a new order according to the order registration request dto - {}, response - {}", dto, orderRegistrationResponseDto);
        return orderRegistrationResponseDto;
    }

    @Override
    public OrderUpdateResponseDto updateOrder(OrderUpdateRequestDto dto) {
        Assert.notNull(dto, "Order update request dto should not be null");
        LOGGER.info("Updating an order according to the order update request dto - {}", dto);
        Optional<Order> orderToUpdateOptional = orderService.findById(dto.getId());
        if(orderToUpdateOptional.isEmpty()) {
            return new ErrorOrderUpdateResponseDto(
                    List.of("Cannot update a non existing order"),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }

        Order orderToUpdate = orderToUpdateOptional.get();
        if(orderToUpdate.getOrderStatusType() != OrderStatusType.OPEN && dto.getOrderStatusType() == OrderStatusType.OPEN) {
            return new ErrorOrderUpdateResponseDto(
                    List.of(String.format("Cannot update the status of an order from %s to %s", orderToUpdate.getOrderStatusType(), dto.getOrderStatusType())),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }

        CafeTableAssignedToWaiter cafeTableAssignedToWaiter = cafeTableAssignedToWaiterService.findByCafeTableId(
                orderToUpdate.getTable().getId()).orElseThrow(() -> new CafeTableNotFoundException(orderToUpdate.getTable().getId())
        );
        String orderCreatorUsername = cafeTableAssignedToWaiter.getWaiter().getUsername();
        String orderUpdatorUsername = dto.getWaiterUsername();
        if(!orderCreatorUsername.equals(orderUpdatorUsername)) {
            return new ErrorOrderUpdateResponseDto(
                    List.of(String.format("Waiter with the username of %s cannot update an order created by a waiter with a username of %s", orderUpdatorUsername, orderCreatorUsername)),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        Order order = orderService.update(orderUpdateParamsMapper.apply(dto));
        if(order.getOrderStatusType() != OrderStatusType.OPEN) {
            cafeTableAssignedToWaiterService.deleteByCafeTableId(dto.getCafeTableId());
            cafeTableService.markAs(order.getTable().getId(), CafeTableStatusType.FREE);
            if(order.getOrderStatusType() == OrderStatusType.CLOSED) {
                productInOrderService.markAllAs(order.getId(), ProductInOrderStatusType.CLOSED);
            } else if(order.getOrderStatusType() == OrderStatusType.CANCELLED) {
                productInOrderService.markAllAs(order.getId(), ProductInOrderStatusType.CANCELLED);
            }
        }

        OrderUpdateResponseDto responseDto = orderUpdateResponseDtoMapper.apply(order);
        LOGGER.info("Successfully updated an order according to the order update request dto - {}, response - {}", dto, responseDto);
        return responseDto;
    }

    public boolean foundTableWithIdAssignedToWaiterWithUsername(Long tableId, String username) {
        List<CafeTableAssignedToWaiter> allByWaiterUsername = cafeTableAssignedToWaiterService.findAllByWaiterUsername(username);
        boolean foundTableWithIdAssignedToWaiterWithUsername = false;
        for(CafeTableAssignedToWaiter cafeTableAssignedToWaiter : allByWaiterUsername) {
            if(cafeTableAssignedToWaiter.getCafeTable().getId().equals(tableId)) {
                foundTableWithIdAssignedToWaiterWithUsername = true;
                break;
            }
        }
        return foundTableWithIdAssignedToWaiterWithUsername;
    }

    @Override
    public OrderListRetrievalResponseDto getAllByWaiterUsername(OrderListRetrievalRequestDto dto) {
        Assert.notNull(dto, "Order list retrieval request dto should not be null");
        LOGGER.info("Retrieving all orders according to the order list retrieval request dto - {}", dto);
        Optional<User> userOptional = userService.findByUsername(dto.getWaiterUsername());
        if(userOptional.isEmpty()) {
            return new ErrorOrderListRetrievalResponseDto(
                    List.of(String.format("User with a username of %s does not exist", dto.getWaiterUsername())),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        if(!userOptional.get().getUserRoleList().stream().map(UserRole::getUserRoleType).collect(Collectors.toList()).contains(UserRoleType.WAITER)){
            return new ErrorOrderListRetrievalResponseDto(
                    List.of(String.format("User with a username of %s is not a waiter", dto.getWaiterUsername())),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        List<OrderRetrievalResponseDto> orderRetrievalResponseDtos = orderService.findAllByWaiterUsername(dto.getWaiterUsername()).stream().map(order -> new OrderRetrievalResponseDto(order.getWaiter().getUsername(), order.getTable().getId(), order.getId(), order.getOrderStatusType(), order.getCreatedAt())).collect(Collectors.toList());
        OrderListRetrievalResponseDto result = new OrderListRetrievalResponseDto(orderRetrievalResponseDtos, HttpStatus.OK);
        LOGGER.info("Successfully retrieved all orders according to the order list retrieval request dto - {}, result - {}", dto, result);
        return result;
    }
}
