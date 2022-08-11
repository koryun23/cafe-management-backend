package com.cafe.dto.response;

import io.jsonwebtoken.lang.Assert;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

public class ProductInOrderListRetrievalResponseDto {
    private List<ProductInOrderRetrievalResponseDto> productInOrderRetrievalResponseDtos;
    private HttpStatus httpStatus;

    public ProductInOrderListRetrievalResponseDto() {
    }

    public ProductInOrderListRetrievalResponseDto(List<ProductInOrderRetrievalResponseDto> productInOrderRetrievalResponseDtos, HttpStatus httpStatus) {
        this.productInOrderRetrievalResponseDtos = productInOrderRetrievalResponseDtos;
        this.httpStatus = httpStatus;
    }

    public List<ProductInOrderRetrievalResponseDto> getProductInOrderRetrievalResponseDtos() {
        return productInOrderRetrievalResponseDtos;
    }

    public void setProductInOrderRetrievalResponseDtos(List<ProductInOrderRetrievalResponseDto> productInOrderRetrievalResponseDtos) {
        Assert.notNull(productInOrderRetrievalResponseDtos, "product in order retrieval dto list should not be null");
        this.productInOrderRetrievalResponseDtos = productInOrderRetrievalResponseDtos;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        Assert.notNull(httpStatus, "http status should not be null");
        this.httpStatus = httpStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInOrderListRetrievalResponseDto that = (ProductInOrderListRetrievalResponseDto) o;
        return Objects.equals(productInOrderRetrievalResponseDtos, that.productInOrderRetrievalResponseDtos) && httpStatus == that.httpStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productInOrderRetrievalResponseDtos, httpStatus);
    }

    @Override
    public String toString() {
        return "ProductInOrderListRetrievalResponseDto{" +
                "productInOrderRetrievalResponseDtos=" + productInOrderRetrievalResponseDtos +
                ", httpStatus=" + httpStatus +
                '}';
    }
}
