package com.cafe.dto;

import com.cafe.entity.order.OrderStatusType;

import java.util.Objects;

public class OrderRegistrationRequestDto {
    private Long cafeTableId;
    private OrderStatusType orderStatusType;

    public OrderRegistrationRequestDto(Long cafeTableId) {
        this.cafeTableId = cafeTableId;
        this.orderStatusType = OrderStatusType.OPEN;
    }

    public OrderRegistrationRequestDto() {
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
        OrderRegistrationRequestDto that = (OrderRegistrationRequestDto) o;
        return Objects.equals(cafeTableId, that.cafeTableId) && orderStatusType == that.orderStatusType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeTableId, orderStatusType);
    }

    @Override
    public String toString() {
        return "OrderRegistrationRequestDto{" +
                "cafeTableId=" + cafeTableId +
                ", orderStatusType=" + orderStatusType +
                '}';
    }
}
