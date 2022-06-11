package com.cafe.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ProductInOrderRegistrationResponseDto {
    private Long productId;
    private Long orderId;
    private Integer amount;
    private LocalDateTime registeredAt;

    private List<String> errors;

    public ProductInOrderRegistrationResponseDto(Long productId, Long orderId, Integer amount, LocalDateTime registeredAt) {
        this.productId = productId;
        this.orderId = orderId;
        this.amount = amount;
        this.registeredAt = registeredAt;
    }

    public ProductInOrderRegistrationResponseDto(List<String> errors) {
        this.errors = errors;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInOrderRegistrationResponseDto that = (ProductInOrderRegistrationResponseDto) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(registeredAt, that.registeredAt) &&
                Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, orderId, amount, registeredAt, errors);
    }

    @Override
    public String toString() {
        return "ProductInOrderRegistrationResponseDto{" +
                "productId=" + productId +
                ", orderId=" + orderId +
                ", amount=" + amount +
                ", registeredAt=" + registeredAt +
                ", errors=" + errors +
                '}';
    }
}
