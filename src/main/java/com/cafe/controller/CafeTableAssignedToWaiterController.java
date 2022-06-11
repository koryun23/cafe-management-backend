package com.cafe.controller;

import com.cafe.dto.CafeTableAssignmentRequestDto;
import com.cafe.dto.CafeTableAssignmentResponseDto;
import com.cafe.facade.core.table.CafeTableFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "TablesWaiters", produces = "application/json", consumes = "application/json")
public class CafeTableAssignedToWaiterController {

    private final CafeTableFacade cafeTableFacade;

    public CafeTableAssignedToWaiterController(CafeTableFacade cafeTableFacade) {
        this.cafeTableFacade = cafeTableFacade;
    }

    @PostMapping(value = "/assign")
    public CafeTableAssignmentResponseDto assignCafeTableToWaiter(@RequestBody CafeTableAssignmentRequestDto requestDto) {
        return cafeTableFacade.assignTableToWaiter(requestDto);
    }
}
