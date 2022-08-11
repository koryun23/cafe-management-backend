package com.cafe.controller;

import com.cafe.dto.request.ProductDeletionRequestDto;
import com.cafe.dto.request.ProductRegistrationRequestDto;
import com.cafe.dto.response.AllProductsRetrievalResponseDto;
import com.cafe.dto.response.ProductDeletionResponseDto;
import com.cafe.dto.response.ProductRegistrationResponseDto;
import com.cafe.dto.request.ProductUpdateRequestDto;
import com.cafe.dto.response.ProductUpdateResponseDto;
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
        ProductRegistrationResponseDto responseDto = productFacade.registerProduct(dto);
        return ResponseEntity
                .status(responseDto.getHttpStatus())
                .body(responseDto);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<ProductUpdateResponseDto> updateProduct(@RequestBody ProductUpdateRequestDto dto,
                                                                  @PathVariable Long id) {
        dto.setId(id);
        ProductUpdateResponseDto responseDto = productFacade.updateProduct(dto);
        return ResponseEntity
                .status(responseDto.getHttpStatus())
                .body(responseDto);
    }

    @GetMapping
    public ResponseEntity<AllProductsRetrievalResponseDto> fetchAll() {
        AllProductsRetrievalResponseDto responseDto = productFacade.fetchAll();
        return ResponseEntity.
                status(responseDto.getHttpStatus())
                .body(responseDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductDeletionResponseDto> delete(@PathVariable Long id) {
        ProductDeletionResponseDto responseDto = productFacade.deleteProduct(new ProductDeletionRequestDto(id));
        return ResponseEntity
                .status(responseDto.getHttpStatus())
                .body(responseDto);
    }
}
