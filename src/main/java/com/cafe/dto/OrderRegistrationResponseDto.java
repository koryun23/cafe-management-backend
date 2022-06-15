package com.cafe.dto;

import com.cafe.entity.order.OrderStatusType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OrderRegistrationResponseDto {

    private Long cafeTableId;
    private OrderStatusType orderStatusType;
    private LocalDateTime registeredAt;

    private List<String> errors;

    public OrderRegistrationResponseDto(Long cafeTableId, OrderStatusType orderStatusType, LocalDateTime registeredAt) {
        this.cafeTableId = cafeTableId;
        this.orderStatusType = orderStatusType;
        this.registeredAt = registeredAt;
    }

    public OrderRegistrationResponseDto() {
    }

    public OrderRegistrationResponseDto(List<String> errors) {
        this.errors = errors;
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

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRegistrationResponseDto that = (OrderRegistrationResponseDto) o;
        return Objects.equals(cafeTableId, that.cafeTableId) &&
                orderStatusType == that.orderStatusType &&
                Objects.equals(registeredAt, that.registeredAt) &&
                Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeTableId, orderStatusType, registeredAt, errors);
    }

    @Override
    public String toString() {
        return "OrderRegistrationResponseDto{" +
                "cafeTableId=" + cafeTableId +
                ", orderStatusType=" + orderStatusType +
                ", registeredAt=" + registeredAt +
                ", errors=" + errors +
                '}';
    }
}
