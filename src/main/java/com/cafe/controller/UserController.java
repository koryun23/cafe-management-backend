package com.cafe.controller;

import com.cafe.dto.UserRegistrationRequestDto;
import com.cafe.dto.UserRegistrationResponseDto;
import com.cafe.facade.core.user.UserFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "users", produces = "application/json", consumes = "application/json")
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping(value = "/register")
    public UserRegistrationResponseDto register(@RequestBody UserRegistrationRequestDto requestDto) {
        return userFacade.registerUser(requestDto);
    }
}
