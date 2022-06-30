package com.cafe.service.core.order;

import com.cafe.entity.order.OrderStatusType;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderCreationParams {

    private Long cafeTableId;
    private OrderStatusType orderStatusType;
    private LocalDateTime createdAt;

    public OrderCreationParams(Long cafeTableId, OrderStatusType orderStatusType, LocalDateTime createdAt) {
        this.cafeTableId = cafeTableId;
        this.orderStatusType = orderStatusType;
        this.createdAt = createdAt;
    }

    public Long getCafeTableId() {
        return cafeTableId;
    }

    public void setCafeTableId(Long cafeTableId) {
        this.cafeTableId = cafeTableId;
    }

    public OrderStatusType getOrderStatusType() {
        return orderStatusType;
    }

    public void setOrderStatusType(OrderStatusType orderStatusType) {
        this.orderStatusType = orderStatusType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCreationParams that = (OrderCreationParams) o;
        return Objects.equals(cafeTableId, that.cafeTableId) &&
                orderStatusType == that.orderStatusType &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeTableId, orderStatusType, createdAt);
    }

    @Override
    public String toString() {
        return "OrderCreationParams{" +
                "cafeTableId=" + cafeTableId +
                ", orderStatusType=" + orderStatusType +
                ", createdAt=" + createdAt +
                '}';
    }
}
