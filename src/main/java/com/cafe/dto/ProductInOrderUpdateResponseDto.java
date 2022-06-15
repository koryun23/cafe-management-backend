package com.cafe.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ProductInOrderUpdateResponseDto {

    private Long id;
    private Long productId;
    private Long orderId;
    private Integer amount;
    private LocalDateTime updatedAt;

    private List<String> errors;
    public ProductInOrderUpdateResponseDto(Long id, Long productId, Long orderId, Integer amount, LocalDateTime updatedAt) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.amount = amount;
        this.updatedAt = updatedAt;
    }

    public ProductInOrderUpdateResponseDto(List<String> errors) {
        this.errors = errors;
    }

    public ProductInOrderUpdateResponseDto() {
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductInOrderUpdateResponseDto{" +
                "id=" + id +
                ", productId=" + productId +
                ", orderId=" + orderId +
                ", amount=" + amount +
                ", updatedAt=" + updatedAt +
                ", errors=" + errors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInOrderUpdateResponseDto that = (ProductInOrderUpdateResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(productId, that.productId) && Objects.equals(orderId, that.orderId) && Objects.equals(amount, that.amount) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, orderId, amount, updatedAt, errors);
    }
}
