package com.cafe.dto.response;

import com.cafe.entity.order.OrderStatusType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OrderUpdateResponseDto {

    private Long id;
    private Long cafeTableId;
    private OrderStatusType orderStatusType;
    private LocalDateTime updatedAt;

    private List<String> errors;

    public OrderUpdateResponseDto(Long id, Long cafeTableId, OrderStatusType orderStatusType, LocalDateTime updatedAt) {
        this.id = id;
        this.cafeTableId = cafeTableId;
        this.orderStatusType = orderStatusType;
        this.updatedAt = updatedAt;
    }

    public OrderUpdateResponseDto() {
    }

    public OrderUpdateResponseDto(List<String> errors) {
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
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
        OrderUpdateResponseDto that = (OrderUpdateResponseDto) o;
        return Objects.equals(cafeTableId, that.cafeTableId) &&
                orderStatusType == that.orderStatusType &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(errors, that.errors) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeTableId, orderStatusType, updatedAt, errors, id);
    }

    @Override
    public String toString() {
        return "OrderUpdateResponseDto{" +
                "id=" + id +
                ", cafeTableId=" + cafeTableId +
                ", orderStatusType=" + orderStatusType +
                ", updatedAt=" + updatedAt +
                ", errors=" + errors +
                '}';
    }
}
