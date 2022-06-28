package com.cafe.dto;

import java.util.Objects;

public class CafeTablesAssignedToWaiterRetrievalRequestDto {

    private String waiterUsername;

    public CafeTablesAssignedToWaiterRetrievalRequestDto() {
    }

    public CafeTablesAssignedToWaiterRetrievalRequestDto(String waiterUsername) {
        this.waiterUsername = waiterUsername;
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
        CafeTablesAssignedToWaiterRetrievalRequestDto that = (CafeTablesAssignedToWaiterRetrievalRequestDto) o;
        return Objects.equals(waiterUsername, that.waiterUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(waiterUsername);
    }

    @Override
    public String toString() {
        return "CafeTableListRetrievalRequestDto{" +
                "waiterUsername=" + waiterUsername +
                '}';
    }
}
