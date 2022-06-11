package com.cafe.controller;

import com.cafe.dto.ProductInOrderRegistrationRequestDto;
import com.cafe.dto.ProductInOrderRegistrationResponseDto;
import com.cafe.dto.ProductInOrderUpdateRequestDto;
import com.cafe.dto.ProductInOrderUpdateResponseDto;
import com.cafe.facade.core.product.ProductFacade;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "ProductInOrder", consumes = "application/json", produces = "application/json")
public class ProductInOrderController {

    private final ProductFacade productFacade;

    public ProductInOrderController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @PostMapping(value = "/register")
    public ProductInOrderRegistrationResponseDto registerProductInOrder(@RequestBody ProductInOrderRegistrationRequestDto dto) {
        return productFacade.registerProductInOrder(dto);
    }

    @PutMapping(value = "/update")
    public ProductInOrderUpdateResponseDto updateProductInOrder(ProductInOrderUpdateRequestDto dto) {
        return productFacade.updateProductInOrder(dto);
    }
}
