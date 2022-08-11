package com.cafe.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

public class AllProductsRetrievalResponseDto {
    private List<ProductRetrievalResponseDto> productRetrievalResponseDtoList;
    private HttpStatus httpStatus;

    public AllProductsRetrievalResponseDto() {
    }

    public AllProductsRetrievalResponseDto(List<ProductRetrievalResponseDto> productRetrievalResponseDtoList, HttpStatus httpStatus) {
        setProductRetrievalResponseDtoList(productRetrievalResponseDtoList);
        setHttpStatus(httpStatus);
    }

    public List<ProductRetrievalResponseDto> getProductRetrievalResponseDtoList() {
        return productRetrievalResponseDtoList;
    }

    public void setProductRetrievalResponseDtoList(List<ProductRetrievalResponseDto> productRetrievalResponseDtoList) {
        Assert.notNull(productRetrievalResponseDtoList, "Product retrieval response dto list should not be null");
        this.productRetrievalResponseDtoList = productRetrievalResponseDtoList;
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
        AllProductsRetrievalResponseDto that = (AllProductsRetrievalResponseDto) o;
        return Objects.equals(productRetrievalResponseDtoList, that.productRetrievalResponseDtoList) && httpStatus == that.httpStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productRetrievalResponseDtoList, httpStatus);
    }

    @Override
    public String toString() {
        return "AllProductsRetrievalResponseDto{" +
                "productRetrievalResponseDtoList=" + productRetrievalResponseDtoList +
                ", httpStatus=" + httpStatus +
                '}';
    }
}
