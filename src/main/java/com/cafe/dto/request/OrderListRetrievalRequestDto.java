package com.cafe.dto.request;

import java.util.Objects;

public class OrderListRetrievalRequestDto {
    private String waiterUsername;

    public OrderListRetrievalRequestDto(String waiterUsername) {
        this.waiterUsername = waiterUsername;
    }

    public OrderListRetrievalRequestDto() {
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
        OrderListRetrievalRequestDto that = (OrderListRetrievalRequestDto) o;
        return Objects.equals(waiterUsername, that.waiterUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(waiterUsername);
    }

    @Override
    public String toString() {
        return "OrderListRetrievalRequestDto{" +
                "waiterUsername='" + waiterUsername + '\'' +
                '}';
    }
}
