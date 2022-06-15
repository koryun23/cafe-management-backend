package com.cafe.controller;

import com.cafe.dto.ProductInOrderRegistrationRequestDto;
import com.cafe.dto.ProductInOrderRegistrationResponseDto;
import com.cafe.dto.ProductInOrderUpdateRequestDto;
import com.cafe.dto.ProductInOrderUpdateResponseDto;
import com.cafe.facade.core.product.ProductFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "ProductInOrder", consumes = "application/json", produces = "application/json")
public class ProductInOrderController {

    private final ProductFacade productFacade;

    public ProductInOrderController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<ProductInOrderRegistrationResponseDto> registerProductInOrder(@RequestBody ProductInOrderRegistrationRequestDto dto) {
        return ResponseEntity.ok(productFacade.registerProductInOrder(dto));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ProductInOrderUpdateResponseDto> updateProductInOrder(ProductInOrderUpdateRequestDto dto) {
        return ResponseEntity.ok(productFacade.updateProductInOrder(dto));
    }
}
