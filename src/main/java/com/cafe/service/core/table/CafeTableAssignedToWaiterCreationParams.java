package com.cafe.service.core.table;

import java.util.Objects;

public class CafeTableAssignedToWaiterCreationParams {

    private Long cafeTableId;
    private Long waiterId;

    public CafeTableAssignedToWaiterCreationParams(Long cafeTableId, Long waiterId) {
        this.cafeTableId = cafeTableId;
        this.waiterId = waiterId;
    }

    public Long getCafeTableId() {
        return cafeTableId;
    }

    public void setCafeTableId(Long cafeTableId) {
        this.cafeTableId = cafeTableId;
    }

    public Long getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Long waiterId) {
        this.waiterId = waiterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeTableAssignedToWaiterCreationParams that = (CafeTableAssignedToWaiterCreationParams) o;
        return Objects.equals(cafeTableId, that.cafeTableId) && Objects.equals(waiterId, that.waiterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeTableId, waiterId);
    }

    @Override
    public String toString() {
        return "CafeTableAssignedToWaiterCreationParams{" +
                "cafeTableId=" + cafeTableId +
                ", waiterId=" + waiterId +
                '}';
    }
}
