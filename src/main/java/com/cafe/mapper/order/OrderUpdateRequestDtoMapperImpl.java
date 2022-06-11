package com.cafe.mapper.order;

import com.cafe.dto.OrderUpdateRequestDto;
import com.cafe.service.core.order.OrderUpdateParams;

public class OrderUpdateRequestDtoMapperImpl implements OrderUpdateRequestDtoMapper{

    @Override
    public OrderUpdateParams apply(OrderUpdateRequestDto dto) {
        return new OrderUpdateParams(
                dto.getId(),
                dto.getCafeTableId(),
                dto.getOrderStatusType()
        );
    }
}
