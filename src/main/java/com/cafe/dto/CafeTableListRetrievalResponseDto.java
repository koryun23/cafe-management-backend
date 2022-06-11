package com.cafe.dto;

import com.cafe.entity.table.CafeTableAssignedToWaiter;

import java.util.List;

public class CafeTableListRetrievalResponseDto {
    private List<CafeTableAssignedToWaiter> cafeTableAssignedToWaiterList;

    public CafeTableListRetrievalResponseDto(List<CafeTableAssignedToWaiter> cafeTableAssignedToWaiterList) {
        this.cafeTableAssignedToWaiterList = cafeTableAssignedToWaiterList;
    }

    public List<CafeTableAssignedToWaiter> getCafeTableAssignedToWaiterList() {
        return cafeTableAssignedToWaiterList;
    }

    public void setCafeTableAssignedToWaiterList(List<CafeTableAssignedToWaiter> cafeTableAssignedToWaiterList) {
        this.cafeTableAssignedToWaiterList = cafeTableAssignedToWaiterList;
    }


}
