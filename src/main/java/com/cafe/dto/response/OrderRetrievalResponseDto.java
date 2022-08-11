package com.cafe.dto.response;

import com.cafe.entity.order.OrderStatusType;
import io.jsonwebtoken.lang.Assert;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderRetrievalResponseDto {
    private String waiterUsername;
    private Long tableId;
    private Long orderId;
    private OrderStatusType orderStatus;
    private LocalDateTime createdAt;

    public OrderRetrievalResponseDto(String waiterUsername,
                                     Long tableId,
                                     Long orderId,
                                     OrderStatusType orderStatus,
                                     LocalDateTime createdAt) {
        this.waiterUsername = waiterUsername;
        this.tableId = tableId;
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
    }

    public String getWaiterUsername() {
        return waiterUsername;
    }

    public void setWaiterUsername(String waiterUsername) {
        Assert.notNull(waiterUsername, "Waiter username should not be null");
        this.waiterUsername = waiterUsername;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        Assert.notNull(tableId, "Table id should not be null");
        this.tableId = tableId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        Assert.notNull(orderId, "Order id should not be null");
        this.orderId = orderId;
    }

    public OrderStatusType getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusType orderStatus) {
        Assert.notNull(orderStatus, "Order status should not be null");
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        Assert.notNull(createdAt, "Creation date should not be null");
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRetrievalResponseDto that = (OrderRetrievalResponseDto) o;
        return Objects.equals(waiterUsername, that.waiterUsername) && Objects.equals(tableId, that.tableId) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(waiterUsername, tableId, orderId, orderStatus, createdAt);
    }

    @Override
    public String toString() {
        return "OrderRetrievalResponseDto{" +
                "waiterUsername='" + waiterUsername + '\'' +
                ", tableId=" + tableId +
                ", orderId=" + orderId +
                ", orderStatus=" + orderStatus +
                ", createdAt=" + createdAt +
                '}';
    }
}
