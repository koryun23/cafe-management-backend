package com.cafe.controller;

import com.cafe.dto.ProductInOrderRegistrationRequestDto;
import com.cafe.dto.ProductInOrderRegistrationResponseDto;
import com.cafe.dto.ProductInOrderUpdateRequestDto;
import com.cafe.dto.ProductInOrderUpdateResponseDto;
import com.cafe.facade.core.product.ProductFacade;
import com.cafe.handler.BasicAuthorizationHttpServletRequestHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "products/order", consumes = "application/json", produces = "application/json")
public class ProductInOrderController {

    private final ProductFacade productFacade;
    private final BasicAuthorizationHttpServletRequestHandler basicAuthorizationHttpServletRequestHandler;

    public ProductInOrderController(ProductFacade productFacade,
                                    BasicAuthorizationHttpServletRequestHandler basicAuthorizationHttpServletRequestHandler) {
        this.productFacade = productFacade;
        this.basicAuthorizationHttpServletRequestHandler = basicAuthorizationHttpServletRequestHandler;
    }

    @PostMapping(path = "/register/{orderId}")
    public ResponseEntity<ProductInOrderRegistrationResponseDto> registerProductInOrder(HttpServletRequest request,
                                                                                        @RequestBody ProductInOrderRegistrationRequestDto dto,
                                                                                        @PathVariable Long orderId) {
        String username = basicAuthorizationHttpServletRequestHandler.getUsernameAndPassword(request).getUsername();
        dto.setWaiterUsername(username);
        dto.setOrderId(orderId);
        return ResponseEntity.ok(productFacade.registerProductInOrder(dto));
    }

    @PutMapping(path = "/update/{productInOrderId}")
    public ResponseEntity<ProductInOrderUpdateResponseDto> updateProductInOrder(HttpServletRequest request,
                                                                                @RequestBody ProductInOrderUpdateRequestDto dto,
                                                                                @PathVariable Long productInOrderId) {
        String username = basicAuthorizationHttpServletRequestHandler.getUsernameAndPassword(request).getUsername();
        dto.setWaiterUsername(username);
        dto.setId(productInOrderId);
        return ResponseEntity.ok(productFacade.updateProductInOrder(dto));
    }
}
