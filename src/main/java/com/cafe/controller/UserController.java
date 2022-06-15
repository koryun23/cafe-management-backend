package com.cafe.controller;

import com.cafe.dto.UserListRetrievalResponseDto;
import com.cafe.dto.UserRegistrationRequestDto;
import com.cafe.dto.UserRegistrationResponseDto;
import com.cafe.facade.core.user.UserFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "users", produces = "application/json", consumes = "application/json")
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<UserRegistrationResponseDto> register(@RequestBody UserRegistrationRequestDto requestDto) {
        UserRegistrationResponseDto registrationResponseDto = userFacade.registerUser(requestDto);
        return ResponseEntity.ok(registrationResponseDto);
    }

    @GetMapping
    public ResponseEntity<UserListRetrievalResponseDto> fetchAll() {
        UserListRetrievalResponseDto userFetchResponseDto = userFacade.getAll();
        return ResponseEntity.ok(userFetchResponseDto);
    }
}
