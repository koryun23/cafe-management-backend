package com.cafe.controller;

import com.cafe.dto.request.ProductDeletionRequestDto;
import com.cafe.dto.request.ProductRegistrationRequestDto;
import com.cafe.dto.response.AllProductsRetrievalResponseDto;
import com.cafe.dto.response.ProductDeletionResponseDto;
import com.cafe.dto.response.ProductRegistrationResponseDto;
import com.cafe.dto.request.ProductUpdateRequestDto;
import com.cafe.dto.response.ProductUpdateResponseDto;
import com.cafe.facade.core.product.ProductFacade;
import com.cafe.service.core.jwt.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "products", consumes = "application/json", produces = "application/json")
public class ProductController {

    private final ProductFacade productFacade;
    private final JwtService jwtService;

    public ProductController(ProductFacade productFacade, JwtService jwtService) {
        this.productFacade = productFacade;
        this.jwtService = jwtService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<ProductRegistrationResponseDto> registerProduct(@RequestBody ProductRegistrationRequestDto dto, HttpServletRequest request) {
        if(jwtService.isExpired(request.getHeader("Authorization").substring(7))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        ProductRegistrationResponseDto responseDto = productFacade.registerProduct(dto);
        return ResponseEntity
                .status(responseDto.getHttpStatus())
                .body(responseDto);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<ProductUpdateResponseDto> updateProduct(@RequestBody ProductUpdateRequestDto dto,
                                                                  @PathVariable Long id,
                                                                  HttpServletRequest request) {
        if(jwtService.isExpired(request.getHeader("Authorization").substring(7))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        dto.setId(id);
        ProductUpdateResponseDto responseDto = productFacade.updateProduct(dto);
        return ResponseEntity
                .status(responseDto.getHttpStatus())
                .body(responseDto);
    }

    @GetMapping
    public ResponseEntity<AllProductsRetrievalResponseDto> fetchAll(HttpServletRequest request) {
        if(jwtService.isExpired(request.getHeader("Authorization").substring(7))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        AllProductsRetrievalResponseDto responseDto = productFacade.fetchAll();
        return ResponseEntity.
                status(responseDto.getHttpStatus())
                .body(responseDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductDeletionResponseDto> delete(@PathVariable Long id, HttpServletRequest request) {
        if(jwtService.isExpired(request.getHeader("Authorization").substring(7))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        ProductDeletionResponseDto responseDto = productFacade.deleteProduct(new ProductDeletionRequestDto(id));
        return ResponseEntity
                .status(responseDto.getHttpStatus())
                .body(responseDto);
    }
}
