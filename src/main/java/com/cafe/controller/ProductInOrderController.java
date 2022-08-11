package com.cafe.controller;

import com.cafe.dto.request.ProductInOrderListRetrievalRequestDto;
import com.cafe.dto.request.ProductInOrderRegistrationRequestDto;
import com.cafe.dto.response.ProductInOrderListRetrievalResponseDto;
import com.cafe.dto.response.ProductInOrderRegistrationResponseDto;
import com.cafe.dto.request.ProductInOrderUpdateRequestDto;
import com.cafe.dto.response.ProductInOrderUpdateResponseDto;
import com.cafe.facade.core.product.ProductFacade;
import com.cafe.handler.BasicAuthorizationHttpServletRequestHandler;
import com.cafe.service.core.jwt.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "products-in-order", produces = "application/json", consumes = "application/json")
public class ProductInOrderController {

    private final ProductFacade productFacade;
    private final JwtService jwtService;

    public ProductInOrderController(ProductFacade productFacade,
                                    JwtService jwtService) {
        this.productFacade = productFacade;
        this.jwtService = jwtService;
    }

    @PostMapping("/register/{orderId}")
    public ResponseEntity<ProductInOrderRegistrationResponseDto> registerProductInOrder(HttpServletRequest request,
                                                                                        @RequestBody ProductInOrderRegistrationRequestDto dto,
                                                                                        @PathVariable Long orderId) {
        String username = jwtService.getUsername(request.getHeader("Authorization").substring(7));
        dto.setWaiterUsername(username);
        dto.setOrderId(orderId);
        ProductInOrderRegistrationResponseDto responseDto = productFacade.registerProductInOrder(dto);
        System.out.println(orderId);
        return ResponseEntity
                .status(responseDto.getHttpStatus())
                .body(responseDto);
    }

    @PutMapping(path = "/update/{productInOrderId}")
    public ResponseEntity<ProductInOrderUpdateResponseDto> updateProductInOrder(HttpServletRequest request,
                                                                                @RequestBody ProductInOrderUpdateRequestDto dto,
                                                                                @PathVariable Long productInOrderId) {
        String username = jwtService.getUsername(request.getHeader("Authorization").substring(7));
        dto.setWaiterUsername(username);
        dto.setId(productInOrderId);
        ProductInOrderUpdateResponseDto responseDto = productFacade.updateProductInOrder(dto);
        return ResponseEntity
                .status(responseDto.getHttpStatus())
                .body(responseDto);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<ProductInOrderListRetrievalResponseDto> fetchAll(HttpServletRequest request, @PathVariable Long orderId){
        String username = jwtService.getUsername(request.getHeader("Authorization").substring(7));
        ProductInOrderListRetrievalRequestDto dto = new ProductInOrderListRetrievalRequestDto(orderId, username);
        ProductInOrderListRetrievalResponseDto result = productFacade.getAllProductsInOrderByOrderId(dto);
        return ResponseEntity.status(result.getHttpStatus()).body(result);
    }
}
