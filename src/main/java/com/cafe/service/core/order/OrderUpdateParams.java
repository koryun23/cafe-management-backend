package com.cafe.service.core.order;

import com.cafe.entity.order.OrderStatusType;

public class OrderUpdateParams {
    private Long id;
    private Long cafeTableId;
    private OrderStatusType orderStatusType;

    public OrderUpdateParams(Long id, Long cafeTableId, OrderStatusType orderStatusType) {
        this.id = id;
        this.cafeTableId = cafeTableId;
        this.orderStatusType = orderStatusType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
