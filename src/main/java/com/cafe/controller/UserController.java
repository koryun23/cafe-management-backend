package com.cafe.controller;

import com.cafe.dto.response.UserListRetrievalResponseDto;
import com.cafe.dto.request.UserRegistrationRequestDto;
import com.cafe.dto.response.UserRegistrationResponseDto;
import com.cafe.facade.core.user.UserFacade;
import com.cafe.service.core.jwt.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "users", produces = "application/json", consumes = "application/json")
public class UserController { // reviewed

    private final UserFacade userFacade;
    private final JwtService jwtService;

    public UserController(UserFacade userFacade, JwtService jwtService) {
        this.userFacade = userFacade;
        this.jwtService = jwtService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<UserRegistrationResponseDto> register(@RequestBody UserRegistrationRequestDto requestDto,
                                                                HttpServletRequest request) {
        if(jwtService.isExpired(request.getHeader("Authorization").substring(7))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        UserRegistrationResponseDto registrationResponseDto = userFacade.registerUser(requestDto);
        return ResponseEntity
                .status(registrationResponseDto.getHttpStatus())
                .body(registrationResponseDto);
    }

    @GetMapping
    public ResponseEntity<UserListRetrievalResponseDto> fetchAll(HttpServletRequest request) {
        if(jwtService.isExpired(request.getHeader("Authorization").substring(7))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        UserListRetrievalResponseDto userFetchResponseDto = userFacade.getAll();
        return ResponseEntity
                .status(userFetchResponseDto.getHttpStatus())
                .body(userFetchResponseDto);
    }
}
