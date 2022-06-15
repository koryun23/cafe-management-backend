package com.cafe.controller;

import com.cafe.dto.OrderRegistrationRequestDto;
import com.cafe.dto.OrderRegistrationResponseDto;
import com.cafe.dto.OrderUpdateRequestDto;
import com.cafe.dto.OrderUpdateResponseDto;
import com.cafe.facade.core.order.OrderFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "orders", consumes = "application/json", produces = "application/json")
public class OrderController {

    private final OrderFacade orderFacade;

    public OrderController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<OrderRegistrationResponseDto> register(@RequestBody OrderRegistrationRequestDto dto) {
        return ResponseEntity.ok(orderFacade.register(dto));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<OrderUpdateResponseDto> updateOrder(@RequestBody OrderUpdateRequestDto dto) {
        return ResponseEntity.ok(orderFacade.updateOrder(dto));
    }

}
