package com.cafe.dto;

import java.util.Objects;

public class CafeTablesAssignedToWaiterRetrievalRequestDto {

    private Long waiterId;

    public CafeTablesAssignedToWaiterRetrievalRequestDto() {
    }

    public CafeTablesAssignedToWaiterRetrievalRequestDto(Long waiterId) {
        this.waiterId = waiterId;
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
        CafeTablesAssignedToWaiterRetrievalRequestDto that = (CafeTablesAssignedToWaiterRetrievalRequestDto) o;
        return Objects.equals(waiterId, that.waiterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(waiterId);
    }

    @Override
    public String toString() {
        return "CafeTableListRetrievalRequestDto{" +
                "waiterId=" + waiterId +
                '}';
    }
}
