package com.cafe.controller;

import com.cafe.dto.ProductRegistrationRequestDto;
import com.cafe.dto.ProductRegistrationResponseDto;
import com.cafe.dto.ProductUpdateRequestDto;
import com.cafe.dto.ProductUpdateResponseDto;
import com.cafe.facade.core.product.ProductFacade;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "products", consumes = "application/json", produces = "application/json")
public class ProductController {

    private final ProductFacade productFacade;

    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @PostMapping(value = "/register")
    public ProductRegistrationResponseDto registerProduct(@RequestBody ProductRegistrationRequestDto dto) {
        return productFacade.registerProduct(dto);
    }

    @PutMapping("/update")
    public ProductUpdateResponseDto updateProduct(ProductUpdateRequestDto dto) {
        return productFacade.updateProduct(dto);
    }
}
