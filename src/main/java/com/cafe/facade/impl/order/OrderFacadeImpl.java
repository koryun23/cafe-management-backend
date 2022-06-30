package com.cafe.facade.impl.order;

import com.cafe.dto.OrderRegistrationRequestDto;
import com.cafe.dto.OrderRegistrationResponseDto;
import com.cafe.dto.OrderUpdateRequestDto;
import com.cafe.dto.OrderUpdateResponseDto;
import com.cafe.entity.order.Order;
import com.cafe.entity.order.OrderStatusType;
import com.cafe.entity.product.ProductInOrder;
import com.cafe.entity.product.ProductInOrderStatusType;
import com.cafe.entity.table.CafeTable;
import com.cafe.entity.table.CafeTableAssignedToWaiter;
import com.cafe.entity.table.CafeTableStatusType;
import com.cafe.facade.core.order.OrderFacade;
import com.cafe.mapper.order.*;
import com.cafe.service.core.order.OrderService;
import com.cafe.service.core.product.ProductInOrderService;
import com.cafe.service.core.product.ProductInOrderUpdateParams;
import com.cafe.service.core.table.CafeTableAssignedToWaiterService;
import com.cafe.service.core.table.CafeTableService;
import com.cafe.service.impl.table.CafeTableAssignedToWaiterException;
import com.cafe.service.impl.table.CafeTableNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Component
public class OrderFacadeImpl implements OrderFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderFacadeImpl.class);
    private final OrderService orderService;
    private final CafeTableService cafeTableService;
    private final CafeTableAssignedToWaiterService cafeTableAssignedToWaiterService;
    private final OrderRegistrationResponseDtoMapper orderRegistrationResponseDtoMapper;
    private final OrderUpdateResponseDtoMapper orderUpdateResponseDtoMapper;
    private final OrderUpdateParamsMapepr orderUpdateRequestDtoMapper;
    private final OrderCreationParamsMapper orderRegistrationRequestDtoMapper;
    private final ProductInOrderService productInOrderService;

    public OrderFacadeImpl(OrderService orderService,
                           CafeTableService cafeTableService,
                           CafeTableAssignedToWaiterService cafeTableAssignedToWaiterService,
                           OrderRegistrationResponseDtoMapper orderRegistrationResponseDtoMapper,
                           OrderUpdateResponseDtoMapper orderUpdateResponseDtoMapper,
                           OrderUpdateParamsMapepr orderUpdateRequestDtoMapper,
                           OrderCreationParamsMapper orderRegistrationRequestDtoMapper,
                           ProductInOrderService productInOrderService) {
        this.orderService = orderService;
        this.cafeTableService = cafeTableService;
        this.cafeTableAssignedToWaiterService = cafeTableAssignedToWaiterService;
        this.orderRegistrationResponseDtoMapper = orderRegistrationResponseDtoMapper;
        this.orderUpdateResponseDtoMapper = orderUpdateResponseDtoMapper;
        this.orderUpdateRequestDtoMapper = orderUpdateRequestDtoMapper;
        this.orderRegistrationRequestDtoMapper = orderRegistrationRequestDtoMapper;
        this.productInOrderService = productInOrderService;
    }

    @Override
    public OrderRegistrationResponseDto register(OrderRegistrationRequestDto dto) {
        Assert.notNull(dto, "Order registration request should not be null");
        LOGGER.info("Registering a new order according to the order registration request dto - {}", dto);
        if(!foundTableWithIdAssignedToWaiterWithUsername(dto.getCafeTableId(), dto.getWaiterUsername())) {
            return new OrderRegistrationResponseDto(
                    List.of(String.format("Table having an id of %d is not assigned to waiter having a username of %s", dto.getCafeTableId(), dto.getWaiterUsername()))
            );
        }
        Optional<CafeTable> cafeTableOptional = cafeTableService.findById(dto.getCafeTableId());
        if(cafeTableOptional.isEmpty()) {
            return new OrderRegistrationResponseDto(List.of(String.format("No table found having an id of %d", dto.getCafeTableId())));
        }
        CafeTable cafeTable = cafeTableOptional.get();
        if(cafeTable.getCafeTableStatusType() != CafeTableStatusType.FREE) {
            return new OrderRegistrationResponseDto(List.of(String.format("The cafe table with an id of %d is not free, its status is %s", dto.getCafeTableId(), cafeTable.getCafeTableStatusType())));
        }
        Order order = orderService.create(orderRegistrationRequestDtoMapper.apply(dto));
        System.out.println(order);
        OrderRegistrationResponseDto orderRegistrationResponseDto = orderRegistrationResponseDtoMapper.apply(order);
        cafeTableService.markAs(order.getTable().getId(), CafeTableStatusType.TAKEN);
        LOGGER.info("Successfully registered a new order according to the order registration request dto - {}, response - {}", dto, orderRegistrationResponseDto);
        return orderRegistrationResponseDto;
    }

    @Override
    public OrderUpdateResponseDto updateOrder(OrderUpdateRequestDto dto) {
        Assert.notNull(dto, "Order update request dto should not be null");
        LOGGER.info("Updating an order according to the order update request dto - {}", dto);
        Order orderToUpdate = orderService.getById(dto.getId());
        if(orderToUpdate.getOrderStatusType() != OrderStatusType.OPEN && dto.getOrderStatusType() == OrderStatusType.OPEN) {
            return new OrderUpdateResponseDto(List.of(
                    String.format("Cannot update the status of an order from %s to %s", orderToUpdate.getOrderStatusType(), dto.getOrderStatusType())
            ));
        }
        Order order = orderService.update(orderUpdateRequestDtoMapper.apply(dto));
        CafeTableAssignedToWaiter cafeTableAssignedToWaiter = cafeTableAssignedToWaiterService.findByCafeTableId(order.getTable().getId()).orElseThrow(() -> new CafeTableNotFoundException(order.getTable().getId()));
        String orderCreatorUsername = cafeTableAssignedToWaiter.getWaiter().getUsername();
        String orderUpdatorUsername = dto.getWaiterUsername();
        if(!orderCreatorUsername.equals(orderUpdatorUsername)) {
            return new OrderUpdateResponseDto(
                    List.of(String.format("Waiter with the username of %s cannot update an order created by a waiter with a username of %s", orderUpdatorUsername, orderCreatorUsername))
            );
        }
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

    private boolean foundTableWithIdAssignedToWaiterWithUsername(Long tableId, String username) {
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
}
