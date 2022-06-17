package com.cafe.controller;

import com.cafe.dto.OrderRegistrationRequestDto;
import com.cafe.dto.OrderRegistrationResponseDto;
import com.cafe.dto.OrderUpdateRequestDto;
import com.cafe.dto.OrderUpdateResponseDto;
import com.cafe.entity.order.OrderStatusType;
import com.cafe.facade.core.order.OrderFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.Base64;

@RestController
@RequestMapping(path = "orders", consumes = "application/json", produces = "application/json")
public class OrderController {

    private final OrderFacade orderFacade;

    public OrderController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<OrderRegistrationResponseDto> register(HttpServletRequest request, @RequestBody OrderRegistrationRequestDto dto) {
        String encodedCredentials = getEncodedCredentials(request);
        String decode = getDecodedCreadentials(encodedCredentials);
        int colonIndex = getSeparatorIndex(decode, ":");
        String username = getUsernameFromDecodedCredentials(decode, colonIndex);
        String password = getPasswordFromDecodedCredentials(decode, colonIndex);
        dto.setOrderStatusType(OrderStatusType.OPEN);
        return ResponseEntity.ok(orderFacade.register(dto));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<OrderUpdateResponseDto> updateOrder(@RequestBody OrderUpdateRequestDto dto) {
        return ResponseEntity.ok(orderFacade.updateOrder(dto));
    }

    private String getEncodedCredentials(HttpServletRequest request) {
        return request.getHeader("Authorization").substring(6);
    }

    private String getDecodedCreadentials(String encodedCredentials) {
        return new String(Base64.getDecoder().decode(encodedCredentials));
    }

    private String getUsernameFromDecodedCredentials(String decodedCredentials, int separatorIndex) {
        return decodedCredentials.substring(0, separatorIndex);
    }

    private String getPasswordFromDecodedCredentials(String decodedCredentials, int separatorIndex) {
        return decodedCredentials.substring(separatorIndex + 1);
    }

    private int getSeparatorIndex(String decodedCredentials, String separator) {
        return decodedCredentials.indexOf(separator);
    }
}
