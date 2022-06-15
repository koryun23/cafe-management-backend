package com.cafe.dto;

import java.util.Objects;

public class ProductInOrderRegistrationRequestDto {

    private Long productId;
    private Long orderId;
    private Integer amount;

    public ProductInOrderRegistrationRequestDto(Long productId, Long orderId, Integer amount) {
        this.productId = productId;
        this.orderId = orderId;
        this.amount = amount;
    }

    public ProductInOrderRegistrationRequestDto() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInOrderRegistrationRequestDto that = (ProductInOrderRegistrationRequestDto) o;
        return Objects.equals(productId, that.productId) && Objects.equals(orderId, that.orderId) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, orderId, amount);
    }

    @Override
    public String toString() {
        return "ProductInOrderRegistrationRequestDto{" +
                "productId=" + productId +
                ", orderId=" + orderId +
                ", amount=" + amount +
                '}';
    }
}
