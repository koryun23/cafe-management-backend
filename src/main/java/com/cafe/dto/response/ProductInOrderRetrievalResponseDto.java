package com.cafe.dto.response;

import com.cafe.entity.product.ProductInOrderStatusType;
import io.jsonwebtoken.lang.Assert;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProductInOrderRetrievalResponseDto {
    private Long productInOrderId;
    private String productName;
    private Integer amount;
    private ProductInOrderStatusType productInOrderStatusType;
    private LocalDateTime registeredAt;
    private HttpStatus httpStatus;
    public ProductInOrderRetrievalResponseDto(Long productInOrderId, String productName, Integer amount, ProductInOrderStatusType productInOrderStatusType, LocalDateTime registeredAt, HttpStatus httpStatus) {
        setProductInOrderId(productInOrderId);
        setProductName(productName);
        setAmount(amount);
        setProductInOrderStatusType(productInOrderStatusType);
        setRegisteredAt(registeredAt);
        setHttpStatus(httpStatus);
    }

    public ProductInOrderRetrievalResponseDto() {
    }

    public Long getProductInOrderId() {
        return productInOrderId;
    }

    public void setProductInOrderId(Long productInOrderId) {
        Assert.notNull(productInOrderId, "Product in order id should not be null");
        this.productInOrderId = productInOrderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        Assert.notNull(productName, "product name should not be null");
        Assert.hasText(productName, "product name should not be empty");
        this.productName = productName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        Assert.notNull(amount, "Amount should not be null");
        this.amount = amount;
    }

    public ProductInOrderStatusType getProductInOrderStatusType() {
        return productInOrderStatusType;
    }

    public void setProductInOrderStatusType(ProductInOrderStatusType productInOrderStatusType) {
        Assert.notNull(productInOrderStatusType, "product in order status type should not be null");
        this.productInOrderStatusType = productInOrderStatusType;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        Assert.notNull(registeredAt, "registration date should not be null");
        this.registeredAt = registeredAt;
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
        ProductInOrderRetrievalResponseDto that = (ProductInOrderRetrievalResponseDto) o;
        return Objects.equals(productInOrderId, that.productInOrderId) && Objects.equals(productName, that.productName) && Objects.equals(amount, that.amount) && productInOrderStatusType == that.productInOrderStatusType && Objects.equals(registeredAt, that.registeredAt) && Objects.equals(httpStatus, that.httpStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productInOrderId, productName, amount, productInOrderStatusType, registeredAt, httpStatus);
    }
}
