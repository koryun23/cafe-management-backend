package com.cafe.dto.request;

import com.cafe.entity.order.OrderStatusType;

import java.util.Objects;

public class OrderUpdateRequestDto {

    private Long id;
    private Long cafeTableId;
    private OrderStatusType orderStatusType;
    private String waiterUsername;

    public OrderUpdateRequestDto(Long id,
                                 Long cafeTableId,
                                 OrderStatusType orderStatusType,
                                 String waiterUsername) {
        this.id = id;
        this.cafeTableId = cafeTableId;
        this.orderStatusType = orderStatusType;
        this.waiterUsername = waiterUsername;
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
        OrderUpdateRequestDto that = (OrderUpdateRequestDto) o;
        return Objects.equals(cafeTableId, that.cafeTableId) &&
                orderStatusType == that.orderStatusType &&
                Objects.equals(id, that.id) &&
                Objects.equals(waiterUsername, that.waiterUsername);
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
                ", waiterUsername=" + waiterUsername +
                '}';
    }
}
