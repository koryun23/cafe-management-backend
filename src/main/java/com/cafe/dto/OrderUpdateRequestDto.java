package com.cafe.dto;

import com.cafe.entity.order.OrderStatusType;

import java.util.Objects;

public class OrderUpdateRequestDto {

    private Long id;
    private Long cafeTableId;
    private OrderStatusType orderStatusType;

    public OrderUpdateRequestDto(Long id, Long cafeTableId, OrderStatusType orderStatusType) {
        this.id = id;
        this.cafeTableId = cafeTableId;
        this.orderStatusType = orderStatusType;
    }

    public OrderUpdateRequestDto() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderUpdateRequestDto that = (OrderUpdateRequestDto) o;
        return Objects.equals(cafeTableId, that.cafeTableId) &&
                orderStatusType == that.orderStatusType &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeTableId, orderStatusType, id);
    }

    @Override
    public String toString() {
        return "OrderUpdateRequestDto{" +
                "id=" + id +
                ",cafeTableId=" + cafeTableId +
                ", orderStatusType=" + orderStatusType +
                '}';
    }
}
