package com.cafe.dto.request;

import io.jsonwebtoken.lang.Assert;

import java.util.Objects;

public class ProductInOrderListRetrievalRequestDto {
    private Long orderId;
    private String waiterUsername;

    public ProductInOrderListRetrievalRequestDto(Long orderId, String waiterUsername) {
        setOrderId(orderId);
        setWaiterUsername(waiterUsername);
    }

    public ProductInOrderListRetrievalRequestDto(Long orderId) {
        setOrderId(orderId);
    }

    public ProductInOrderListRetrievalRequestDto() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        Assert.notNull(orderId, "Order id should not be null");
        this.orderId = orderId;
    }

    public String getWaiterUsername() {
        return waiterUsername;
    }

    public void setWaiterUsername(String waiterUsername) {
        Assert.notNull(waiterUsername, "Waiter username should not be null");
        Assert.notNull(waiterUsername, "Waiter username should not be empty");
        this.waiterUsername = waiterUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInOrderListRetrievalRequestDto that = (ProductInOrderListRetrievalRequestDto) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(waiterUsername, that.waiterUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, waiterUsername);
    }

    @Override
    public String toString() {
        return "ProductInOrderListRetrievalRequestDto{" +
                "orderId=" + orderId +
                "waiterUsername=" + waiterUsername +
                '}';
    }
}
