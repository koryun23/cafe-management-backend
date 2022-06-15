package com.cafe.controller;

import com.cafe.dto.ProductRegistrationRequestDto;
import com.cafe.dto.ProductRegistrationResponseDto;
import com.cafe.dto.ProductUpdateRequestDto;
import com.cafe.dto.ProductUpdateResponseDto;
import com.cafe.facade.core.product.ProductFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "products", consumes = "application/json", produces = "application/json")
public class ProductController {

    private final ProductFacade productFacade;

    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<ProductRegistrationResponseDto> registerProduct(@RequestBody ProductRegistrationRequestDto dto) {
        return ResponseEntity.ok(productFacade.registerProduct(dto));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ProductUpdateResponseDto> updateProduct(ProductUpdateRequestDto dto) {
        return ResponseEntity.ok(productFacade.updateProduct(dto));
    }
}
