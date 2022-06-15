package com.cafe.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class CafeTableAssignmentResponseDto {

    private Long tableId;
    private Long waiterId;
    private LocalDateTime assignedAt;

    private List<String> errors;

    public CafeTableAssignmentResponseDto(Long tableId, Long waiterId, LocalDateTime assignedAt) {
        this.tableId = tableId;
        this.waiterId = waiterId;
        this.assignedAt = assignedAt;
    }

    public CafeTableAssignmentResponseDto(List<String> errors) {
        this.errors = errors;
    }

    public CafeTableAssignmentResponseDto() {
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

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeTableAssignmentResponseDto that = (CafeTableAssignmentResponseDto) o;
        return Objects.equals(tableId, that.tableId) &&
                Objects.equals(waiterId, that.waiterId) &&
                Objects.equals(assignedAt, that.assignedAt) &&
                Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, waiterId, assignedAt, errors);
    }

    @Override
    public String toString() {
        return "CafeTableAssignmentResponseDto{" +
                "tableId=" + tableId +
                ", waiterId=" + waiterId +
                ", assignedAt=" + assignedAt +
                ", errors=" + errors +
                '}';
    }


}
