package com.cafe.dto.response;

import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ProductInOrderRegistrationResponseDto {
    private String productName;
    private Long orderId;
    private Integer amount;
    private LocalDateTime registeredAt;

    public ProductInOrderRegistrationResponseDto(String productName, Long orderId, Integer amount, LocalDateTime registeredAt) {
        setProductName(productName);
        setOrderId(orderId);
        setAmount(amount);
        setRegisteredAt(registeredAt);
    }

    public ProductInOrderRegistrationResponseDto() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        Assert.notNull(productName, "Product name should not be null");
        Assert.hasText(productName, "Product name should not be empty");
        this.productName = productName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        Assert.notNull(orderId, "Order id should not be null");
        this.orderId = orderId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        Assert.notNull(amount, "Amount should not be null");
        if(amount <= 0) {
            throw new IllegalArgumentException("Amount should be > 0");
        }
        this.amount = amount;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        Assert.notNull(registeredAt, "Registration date should not be null");
        this.registeredAt = registeredAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInOrderRegistrationResponseDto that = (ProductInOrderRegistrationResponseDto) o;
        return Objects.equals(productName, that.productName) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(registeredAt, that.registeredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, orderId, amount, registeredAt);
    }

    @Override
    public String toString() {
        return "ProductInOrderRegistrationResponseDto{" +
                "productName=" + productName +
                ", orderId=" + orderId +
                ", amount=" + amount +
                ", registeredAt=" + registeredAt +
                '}';
    }
}
