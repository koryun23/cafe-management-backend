package com.cafe.dto;

import java.util.Objects;

public class CafeTableAssignmentRequestDto {

    private Long cafeTableId;
    private Long waiterId;

    public CafeTableAssignmentRequestDto(Long tableId, Long waiterId) {
        this.cafeTableId = tableId;
        this.waiterId = waiterId;
    }

    public CafeTableAssignmentRequestDto() {
    }

    public Long getCafeTableId() {
        return cafeTableId;
    }

    public void setCafeTableId(Long tableId) {
        this.cafeTableId = tableId;
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
        CafeTableAssignmentRequestDto that = (CafeTableAssignmentRequestDto) o;
        return Objects.equals(cafeTableId, that.cafeTableId) &&
                Objects.equals(waiterId, that.waiterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeTableId, waiterId);
    }

    @Override
    public String toString() {
        return "CafeTableAssignmentRequestDto{" +
                "tableId=" + cafeTableId +
                ", waiterId=" + waiterId +
                '}';
    }
}
