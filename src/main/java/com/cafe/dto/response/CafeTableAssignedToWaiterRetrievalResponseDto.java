package com.cafe.dto.response;

import io.jsonwebtoken.lang.Assert;

import java.time.LocalDateTime;
import java.util.Objects;

public class CafeTableAssignedToWaiterRetrievalResponseDto {

    private Long cafeTableId;
    private String waiterUsername;
    private LocalDateTime assignedAt;

    public CafeTableAssignedToWaiterRetrievalResponseDto() {
    }

    public CafeTableAssignedToWaiterRetrievalResponseDto(Long cafeTableId, String waiterUsername, LocalDateTime assignedAt) {
        setCafeTableId(cafeTableId);
        setWaiterUsername(waiterUsername);
        setAssignedAt(assignedAt);
    }

    public Long getCafeTableId() {
        return cafeTableId;
    }

    public void setCafeTableId(Long cafeTableId) {
        Assert.notNull(cafeTableId, "Cafe table id should not be null");
        this.cafeTableId = cafeTableId;
    }

    public String getWaiterUsername() {
        return waiterUsername;
    }

    public void setWaiterUsername(String waiterUsername) {
        Assert.notNull(waiterUsername, "Waiter username should not be null");
        this.waiterUsername = waiterUsername;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        Assert.notNull(assignedAt, "Assignment date should not be null");
        this.assignedAt = assignedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeTableAssignedToWaiterRetrievalResponseDto that = (CafeTableAssignedToWaiterRetrievalResponseDto) o;
        return Objects.equals(cafeTableId, that.cafeTableId) && Objects.equals(waiterUsername, that.waiterUsername) && Objects.equals(assignedAt, that.assignedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeTableId, waiterUsername, assignedAt);
    }

    @Override
    public String toString() {
        return "CafeTableAssignedToWaiterRetrievalResponseDto{" +
                "cafeTableId=" + cafeTableId +
                ", waiterUsername='" + waiterUsername + '\'' +
                ", assignedAt=" + assignedAt +
                '}';
    }
}
