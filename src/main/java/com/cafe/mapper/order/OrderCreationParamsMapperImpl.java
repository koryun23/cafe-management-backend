package com.cafe.mapper.order;

import com.cafe.dto.OrderRegistrationRequestDto;
import com.cafe.service.core.order.OrderCreationParams;

public class OrderCreationParamsMapperImpl implements OrderCreationParamsMapper {

    @Override
    public OrderCreationParams apply(OrderRegistrationRequestDto dto) {
        return new OrderCreationParams(
                dto.getCafeTableId(),
                dto.getOrderStatusType()
        );
    }
}
