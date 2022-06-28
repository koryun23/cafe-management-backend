package com.cafe.dto;

import java.util.Objects;

public class ProductInOrderUpdateRequestDto {

    private Long id;
    private Long productId;
    private Long orderId;
    private Integer amount;
    private String waiterUsername;

    public ProductInOrderUpdateRequestDto(Long id,
                                          Long productId,
                                          Long orderId,
                                          Integer amount,
                                          String waiterUsername) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.amount = amount;
        this.waiterUsername = waiterUsername;
    }

    public ProductInOrderUpdateRequestDto() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWaiterUsername() {
        return waiterUsername;
    }

    public void setWaiterUsername(String waiterUsername) {
        this.waiterUsername = waiterUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInOrderUpdateRequestDto that = (ProductInOrderUpdateRequestDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(waiterUsername, that.waiterUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, orderId, amount, waiterUsername);
    }

    @Override
    public String toString() {
        return "ProductInOrderUpdateRequestDto{" +
                "id=" + id +
                ", productId=" + productId +
                ", orderId=" + orderId +
                ", amount=" + amount +
                ", waiterUsername=" + waiterUsername +
                '}';
    }
}
