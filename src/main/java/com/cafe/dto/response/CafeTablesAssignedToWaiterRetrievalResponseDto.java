package com.cafe.dto.response;

import com.cafe.entity.table.CafeTableAssignedToWaiter;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

public class CafeTablesAssignedToWaiterRetrievalResponseDto {
    private List<CafeTableAssignedToWaiter> cafeTableAssignedToWaiterList;

    public CafeTablesAssignedToWaiterRetrievalResponseDto(List<CafeTableAssignedToWaiter> cafeTableAssignedToWaiterList) {
        setCafeTableAssignedToWaiterList(cafeTableAssignedToWaiterList);
    }

    public CafeTablesAssignedToWaiterRetrievalResponseDto() {
    }

    public List<CafeTableAssignedToWaiter> getCafeTableAssignedToWaiterList() {
        return cafeTableAssignedToWaiterList;
    }

    public void setCafeTableAssignedToWaiterList(List<CafeTableAssignedToWaiter> cafeTableAssignedToWaiterList) {
        Assert.notNull(cafeTableAssignedToWaiterList, "List of cafe tables assigned to waiter should not be null");
        this.cafeTableAssignedToWaiterList = cafeTableAssignedToWaiterList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeTablesAssignedToWaiterRetrievalResponseDto that = (CafeTablesAssignedToWaiterRetrievalResponseDto) o;
        return Objects.equals(cafeTableAssignedToWaiterList, that.cafeTableAssignedToWaiterList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeTableAssignedToWaiterList);
    }

    @Override
    public String toString() {
        return "CafeTablesAssignedToWaiterRetrievalResponseDto{" +
                "cafeTableAssignedToWaiterList=" + cafeTableAssignedToWaiterList +
                '}';
    }
}
