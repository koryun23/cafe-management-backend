package com.cafe.mapper.order;

import com.cafe.dto.OrderUpdateRequestDto;
import com.cafe.service.core.order.OrderUpdateParams;

import java.util.function.Function;

public interface OrderUpdateParamsMapepr extends Function<OrderUpdateRequestDto, OrderUpdateParams> {

    OrderUpdateParams apply(OrderUpdateRequestDto dto);
}
