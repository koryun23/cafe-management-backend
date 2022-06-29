package com.cafe.service.core.table;

import java.util.Objects;

public class CafeTableAssignedToWaiterCreationParams {

    private Long cafeTableId;
    private String waiterUsername;

    public CafeTableAssignedToWaiterCreationParams(Long cafeTableId, String waiterUsername) {
        this.cafeTableId = cafeTableId;
        this.waiterUsername = waiterUsername;
    }

    public Long getCafeTableId() {
        return cafeTableId;
    }

    public void setCafeTableId(Long cafeTableId) {
        this.cafeTableId = cafeTableId;
    }

    public String getWaiterUsername() {
        return waiterUsername;
    }

    public void setWaiterUsername(String waiterId) {
        this.waiterUsername = waiterUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeTableAssignedToWaiterCreationParams that = (CafeTableAssignedToWaiterCreationParams) o;
        return Objects.equals(cafeTableId, that.cafeTableId) &&
                Objects.equals(waiterUsername, that.waiterUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeTableId, waiterUsername);
    }

    @Override
    public String toString() {
        return "CafeTableAssignedToWaiterCreationParams{" +
                "cafeTableId=" + cafeTableId +
                ", waiterUsername=" + waiterUsername +
                '}';
    }
}
