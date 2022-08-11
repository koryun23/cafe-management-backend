package com.cafe.controller;

import com.cafe.dto.request.OrderListRetrievalRequestDto;
import com.cafe.dto.response.OrderListRetrievalResponseDto;
import com.cafe.handler.BasicAuthorizationHttpServletRequestHandler;
import com.cafe.dto.request.OrderRegistrationRequestDto;
import com.cafe.dto.response.OrderRegistrationResponseDto;
import com.cafe.dto.request.OrderUpdateRequestDto;
import com.cafe.dto.response.OrderUpdateResponseDto;
import com.cafe.entity.order.OrderStatusType;
import com.cafe.facade.core.order.OrderFacade;
import com.cafe.service.core.jwt.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "orders", consumes = "application/json", produces = "application/json")
public class OrderController {

    private final OrderFacade orderFacade;
    private final JwtService jwtService;

    public OrderController(OrderFacade orderFacade,
                           JwtService jwtService) {
        this.orderFacade = orderFacade;
        this.jwtService = jwtService;
    }

    @PostMapping(path = "/register/{cafeTableId}")
    public ResponseEntity<OrderRegistrationResponseDto> register(HttpServletRequest request,
                                                                 @PathVariable Long cafeTableId) {
        String username = jwtService.getUsername(request.getHeader("Authorization").substring(7));
        OrderRegistrationRequestDto dto = new OrderRegistrationRequestDto(cafeTableId, username);
        OrderRegistrationResponseDto responseDto = orderFacade.register(dto);
        return ResponseEntity
                .status(responseDto.getHttpStatus())
                .body(responseDto);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<OrderUpdateResponseDto> updateOrder(HttpServletRequest request,
                                                              @RequestBody OrderUpdateRequestDto dto,
                                                              @PathVariable Long id) {
        String username = jwtService.getUsername(request.getHeader("Authorization").substring(7));
        dto.setWaiterUsername(username);
        dto.setId(id);
        OrderUpdateResponseDto responseDto = orderFacade.updateOrder(dto);
        return ResponseEntity
                .status(responseDto.getHttpStatus())
                .body(responseDto);
    }

    @GetMapping
    public ResponseEntity<OrderListRetrievalResponseDto> fetchAll(HttpServletRequest request) {
        String username = jwtService.getUsername(request.getHeader("Authorization").substring(7));
        OrderListRetrievalResponseDto result = orderFacade.getAllByWaiterUsername(new OrderListRetrievalRequestDto(username));
        return ResponseEntity
                .status(result.getHttpStatus())
                .body(result);
    }
}
