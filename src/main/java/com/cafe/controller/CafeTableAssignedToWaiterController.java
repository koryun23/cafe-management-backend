package com.cafe.controller;

import com.cafe.dto.request.CafeTableAssignmentRequestDto;
import com.cafe.dto.response.CafeTableAssignmentResponseDto;
import com.cafe.dto.request.CafeTablesAssignedToWaiterRetrievalRequestDto;
import com.cafe.dto.response.CafeTablesAssignedToWaiterRetrievalResponseDto;
import com.cafe.facade.core.table.CafeTableFacade;
import com.cafe.handler.BasicAuthorizationHttpServletRequestHandler;
import com.cafe.service.core.jwt.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "tables-to-waiters", produces = "application/json", consumes = "application/json")
public class CafeTableAssignedToWaiterController {

    private final CafeTableFacade cafeTableFacade;
    private final JwtService jwtService;

    public CafeTableAssignedToWaiterController(CafeTableFacade cafeTableFacade, JwtService jwtService) {
        this.cafeTableFacade = cafeTableFacade;
        this.jwtService = jwtService;
    }

    @PostMapping(path = "/assign")
    public ResponseEntity<CafeTableAssignmentResponseDto> assignCafeTableToWaiter(@RequestBody CafeTableAssignmentRequestDto requestDto) {
        CafeTableAssignmentResponseDto responseDto = cafeTableFacade.assignTableToWaiter(requestDto);
        return ResponseEntity
                .status(responseDto.getHttpStatus())
                .body(responseDto);
    }

    @GetMapping
    public ResponseEntity<CafeTablesAssignedToWaiterRetrievalResponseDto> retrieveCafeTablesAssignedToWaiter(HttpServletRequest request) {
        String username = jwtService.getUsername(request.getHeader("Authorization").substring(7));
        CafeTablesAssignedToWaiterRetrievalRequestDto dto = new CafeTablesAssignedToWaiterRetrievalRequestDto(username);
        dto.setWaiterUsername(username);
        CafeTablesAssignedToWaiterRetrievalResponseDto responseDto = cafeTableFacade.retrieveCafeTableAssignedToWaiterList(dto);
        return ResponseEntity.status(responseDto.getHttpStatus()).body(responseDto);
    }
}
