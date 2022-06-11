package com.cafe.controller;

import com.cafe.dto.OrderRegistrationRequestDto;
import com.cafe.dto.OrderRegistrationResponseDto;
import com.cafe.dto.OrderUpdateRequestDto;
import com.cafe.dto.OrderUpdateResponseDto;
import com.cafe.facade.core.order.OrderFacade;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "orders", consumes = "application/json", produces = "application/json")
public class OrderController {

    private final OrderFacade orderFacade;

    public OrderController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @PostMapping(value = "/register")
    public OrderRegistrationResponseDto register(@RequestBody OrderRegistrationRequestDto dto) {
        return orderFacade.register(dto);
    }

    @PutMapping(value = "/update")
    public OrderUpdateResponseDto updateOrder(@RequestBody OrderUpdateRequestDto dto) {
        return orderFacade.updateOrder(dto);
    }

}
