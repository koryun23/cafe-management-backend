package com.cafe.dto;

import java.util.Objects;

public class CafeTableAssignmentRequestDto {

    private Long tableId;
    private Long waiterId;

    public CafeTableAssignmentRequestDto(Long tableId, Long waiterId) {
        this.tableId = tableId;
        this.waiterId = waiterId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
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
        return Objects.equals(tableId, that.tableId) && Objects.equals(waiterId, that.waiterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, waiterId);
    }

    @Override
    public String toString() {
        return "CafeTableAssignmentRequestDto{" +
                "tableId=" + tableId +
                ", waiterId=" + waiterId +
                '}';
    }
}
