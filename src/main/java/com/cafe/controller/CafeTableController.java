package com.cafe.controller;

import com.cafe.dto.CafeTableListRetrievalRequestDto;
import com.cafe.dto.CafeTableListRetrievalResponseDto;
import com.cafe.dto.CafeTableRegistrationRequestDto;
import com.cafe.dto.CafeTableRegistrationResponseDto;
import com.cafe.facade.core.table.CafeTableFacade;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "tables", consumes = "application/json", produces = "application/json")
public class CafeTableController {

    private final CafeTableFacade cafeTableFacade;

    public CafeTableController(CafeTableFacade cafeTableFacade) {
        this.cafeTableFacade = cafeTableFacade;
    }

    @PostMapping(value = "/register")
    public CafeTableRegistrationResponseDto register(@RequestBody CafeTableRegistrationRequestDto requestDto) {
        return cafeTableFacade.register(requestDto);
    }

    @GetMapping(value = "/retrieve")
    public CafeTableListRetrievalResponseDto retrieveCafeTableList(@RequestBody CafeTableListRetrievalRequestDto requestDto) {
        return cafeTableFacade.retrieveCafeTableList(requestDto);
    }
}
