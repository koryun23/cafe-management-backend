package com.cafe.mapper.order;

import com.cafe.dto.OrderUpdateRequestDto;
import com.cafe.service.core.order.OrderUpdateParams;
import org.springframework.stereotype.Component;

@Component
public class OrderUpdateParamsMapperImpl implements OrderUpdateParamsMapepr {

    @Override
    public OrderUpdateParams apply(OrderUpdateRequestDto dto) {
        return new OrderUpdateParams(
                dto.getId(),
                dto.getCafeTableId(),
                dto.getOrderStatusType()
        );
    }
}
