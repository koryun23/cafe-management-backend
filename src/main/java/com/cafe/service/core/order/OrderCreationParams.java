package com.cafe.service.core.order;

import com.cafe.entity.order.OrderStatusType;

import java.util.Objects;

public class OrderCreationParams {

    private Long cafeTableId;
    private OrderStatusType orderStatusType;

    public OrderCreationParams(Long cafeTableId, OrderStatusType orderStatusType) {
        this.cafeTableId = cafeTableId;
        this.orderStatusType = orderStatusType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCreationParams that = (OrderCreationParams) o;
        return Objects.equals(cafeTableId, that.cafeTableId) && orderStatusType == that.orderStatusType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeTableId, orderStatusType);
    }

    @Override
    public String toString() {
        return "OrderCreationParams{" +
                "cafeTableId=" + cafeTableId +
                ", orderStatusType=" + orderStatusType +
                '}';
    }
}
