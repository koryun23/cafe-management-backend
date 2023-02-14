package com.cafe.controller;

import com.cafe.dto.request.CafeTableRegistrationRequestDto;
import com.cafe.dto.response.AllCafeTablesRetrievalResponseDto;
import com.cafe.dto.response.CafeTableRegistrationResponseDto;
import com.cafe.entity.table.CafeTableStatusType;
import com.cafe.facade.core.table.CafeTableFacade;
import com.cafe.service.core.jwt.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "tables", consumes = "application/json", produces = "application/json")
public class CafeTableController {

    private final CafeTableFacade cafeTableFacade;
    private final JwtService jwtService;

    public CafeTableController(CafeTableFacade cafeTableFacade, JwtService jwtService) {
        this.cafeTableFacade = cafeTableFacade;
        this.jwtService = jwtService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<CafeTableRegistrationResponseDto> register(@RequestBody CafeTableRegistrationRequestDto requestDto,
                                                                     HttpServletRequest request) {
        if(jwtService.isExpired(request.getHeader("Authorization").substring(7))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        requestDto.setCafeTableStatusType(CafeTableStatusType.FREE);
        CafeTableRegistrationResponseDto responseDto = cafeTableFacade.register(requestDto);
        return ResponseEntity
                .status(responseDto.getHttpStatus())
                .body(responseDto);
    }

    @GetMapping
    public ResponseEntity<AllCafeTablesRetrievalResponseDto> fetchAll(HttpServletRequest request) {
        if(jwtService.isExpired(request.getHeader("Authorization").substring(7))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        AllCafeTablesRetrievalResponseDto responseDto = cafeTableFacade.retrieveAllCafeTables();
        return ResponseEntity
                .status(responseDto.getHttpStatus())
                .body(responseDto);
    }
}
