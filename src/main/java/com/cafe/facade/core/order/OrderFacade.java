package com.cafe.facade.core.order;

import com.cafe.dto.OrderRegistrationRequestDto;
import com.cafe.dto.OrderRegistrationResponseDto;
import com.cafe.dto.OrderUpdateRequestDto;
import com.cafe.dto.OrderUpdateResponseDto;

public interface OrderFacade {
    OrderRegistrationResponseDto register(OrderRegistrationRequestDto dto);

    OrderUpdateResponseDto updateOrder(OrderUpdateRequestDto dto);

}
