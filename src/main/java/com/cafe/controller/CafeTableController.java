package com.cafe.controller;

import com.cafe.dto.CafeTablesAssignedToWaiterRetrievalRequestDto;
import com.cafe.dto.CafeTablesAssignedToWaiterRetrievalResponseDto;
import com.cafe.dto.CafeTableRegistrationRequestDto;
import com.cafe.dto.CafeTableRegistrationResponseDto;
import com.cafe.facade.core.table.CafeTableFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "tables", consumes = "application/json", produces = "application/json")
public class CafeTableController {

    private final CafeTableFacade cafeTableFacade;

    public CafeTableController(CafeTableFacade cafeTableFacade) {
        this.cafeTableFacade = cafeTableFacade;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<CafeTableRegistrationResponseDto> register(@RequestBody CafeTableRegistrationRequestDto requestDto) {
        return ResponseEntity.ok(cafeTableFacade.register(requestDto));
    }

    @GetMapping
    public ResponseEntity<CafeTablesAssignedToWaiterRetrievalResponseDto> retrieveCafeTablesAssignedToWaiter(@RequestBody CafeTablesAssignedToWaiterRetrievalRequestDto requestDto) {
        return ResponseEntity.ok(cafeTableFacade.retrieveCafeTableList(requestDto));
    }
}
