package com.cafe.dto.request;

import java.util.Objects;

public class CafeTableAssignmentRequestDto {

    private Long cafeTableId;
    private String waiterUsername;

    public CafeTableAssignmentRequestDto(Long tableId, String waiterUsername) {
        this.cafeTableId = tableId;
        this.waiterUsername = waiterUsername;
    }

    public CafeTableAssignmentRequestDto() {
    }

    public Long getCafeTableId() {
        return cafeTableId;
    }

    public void setCafeTableId(Long tableId) {
        this.cafeTableId = tableId;
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
        CafeTableAssignmentRequestDto that = (CafeTableAssignmentRequestDto) o;
        return Objects.equals(cafeTableId, that.cafeTableId) &&
                Objects.equals(waiterUsername, that.waiterUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeTableId, waiterUsername);
    }

    @Override
    public String toString() {
        return "CafeTableAssignmentRequestDto{" +
                "tableId=" + cafeTableId +
                ", waiterUsername=" + waiterUsername +
                '}';
    }
}
