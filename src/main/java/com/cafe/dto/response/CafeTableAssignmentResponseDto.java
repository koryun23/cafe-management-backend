package com.cafe.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class CafeTableAssignmentResponseDto {

    private Long tableId;
    private String waiterUsername;
    private LocalDateTime assignedAt;

    private List<String> errors;

    public CafeTableAssignmentResponseDto(Long tableId, String waiterUsername, LocalDateTime assignedAt) {
        this.tableId = tableId;
        this.waiterUsername = waiterUsername;
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

    public String getWaiterUsername() {
        return waiterUsername;
    }

    public void setWaiterUsername(String waiterUsername) {
        this.waiterUsername = waiterUsername;
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
                Objects.equals(waiterUsername, that.waiterUsername) &&
                Objects.equals(assignedAt, that.assignedAt) &&
                Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, waiterUsername, assignedAt, errors);
    }

    @Override
    public String toString() {
        return "CafeTableAssignmentResponseDto{" +
                "tableId=" + tableId +
                ", waiterUsername=" + waiterUsername +
                ", assignedAt=" + assignedAt +
                ", errors=" + errors +
                '}';
    }


}
