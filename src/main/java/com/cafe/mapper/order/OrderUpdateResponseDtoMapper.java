package com.cafe.mapper.order;

import com.cafe.dto.OrderUpdateResponseDto;
import com.cafe.entity.order.Order;

import java.util.function.Function;

public interface OrderUpdateResponseDtoMapper extends Function<Order, OrderUpdateResponseDto> {

    OrderUpdateResponseDto apply(Order order);
}
