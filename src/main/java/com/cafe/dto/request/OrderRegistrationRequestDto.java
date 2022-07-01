package com.cafe.dto.request;

import com.cafe.entity.order.OrderStatusType;

import java.util.Objects;

public class OrderRegistrationRequestDto {

    private Long cafeTableId;
    private OrderStatusType status;
    private String waiterUsername;

    public OrderRegistrationRequestDto(Long cafeTableId, String waiterUsername) {
        this.cafeTableId = cafeTableId;
        this.status = OrderStatusType.OPEN;
        this.waiterUsername = waiterUsername;
    }

    public OrderRegistrationRequestDto() {
    }

    public Long getCafeTableId() {
        return cafeTableId;
    }

    public void setCafeTableId(Long cafeTableId) {
        this.cafeTableId = cafeTableId;
    }

    public OrderStatusType getStatus() {
        return status;
    }

    public void setStatus(OrderStatusType status) {
        this.status = status;
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
        OrderRegistrationRequestDto that = (OrderRegistrationRequestDto) o;
        return Objects.equals(cafeTableId, that.cafeTableId) &&
                status == that.status &&
                Objects.equals(waiterUsername, that.waiterUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeTableId, status, waiterUsername);
    }

    @Override
    public String toString() {
        return "OrderRegistrationRequestDto{" +
                "cafeTableId=" + cafeTableId +
                ", status=" + status +
                '}';
    }
}
