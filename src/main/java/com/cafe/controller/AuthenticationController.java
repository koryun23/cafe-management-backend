package com.cafe.controller;

import com.cafe.dto.response.AuthenticationResponseDto;
import com.cafe.entity.user.User;
import com.cafe.security.UsernamePasswordHolder;
import com.cafe.service.core.jwt.JwtService;
import com.cafe.service.core.user.UserService;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.Date;

@RestController
@RequestMapping(path = "login")
public class AuthenticationController {

    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    public AuthenticationController(JwtService jwtService,
                                    PasswordEncoder passwordEncoder,
                                    UserService userService) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping
    public void login(@RequestBody UsernamePasswordHolder usernamePasswordHolder, HttpServletResponse response) {
//        System.out.println("AUTHENTICATING");
//        User userWithRequestedUsername = userService.getByUsername(usernamePasswordHolder.getUsername());
////        if(!passwordEncoder.matches(usernamePasswordHolder.getPassword(), userWithRequestedUsername.getPassword())) {
////            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new AuthenticationResponseDto(
////                    "User not authenticated"
////            ));
////        }
//        String token = jwtService.createToken(usernamePasswordHolder.getUsername(), new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7));
//        response.setHeader("token", token);
//        return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponseDto(
//                token,
//                userWithRequestedUsername.getUsername(),
//                userWithRequestedUsername.getPassword(),
//                userWithRequestedUsername.getFirstName(),
//                userWithRequestedUsername.getSecondName()
//        ));
    }
}
