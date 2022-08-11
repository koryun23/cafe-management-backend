package com.cafe.security;

import com.cafe.dto.response.AuthenticationResponseDto;
import com.cafe.entity.user.User;
import com.cafe.entity.user.UserRole;
import com.cafe.service.core.jwt.JwtService;
import com.cafe.service.core.user.UserRoleService;
import com.cafe.service.core.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.security.config.Elements.JWT;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Value("${jwt.secret.key}")
    private String secretKey;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final JwtService jwtService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   JwtService jwtService ,
                                   UserService userService,
                                   UserRoleService userRoleService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
        this.userRoleService = userRoleService;
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        System.out.println("Attempting authentication");
        try {
            User creds = new ObjectMapper()
                    .readValue(request.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            //creds.getUserRoleList().stream().map(userRole -> new SimpleGrantedAuthority(userRole.getUserRoleType().toString())).collect(Collectors.toList())
                            Collections.emptyList()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        String username = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
        String token = jwtService.createToken(username, new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7));

        User user = userService.getByUsername(username);
        AuthenticationResponseDto body = new AuthenticationResponseDto(
                token,
                username,
                user.getPassword(),
                user.getFirstName(),
                user.getSecondName(),
                userRoleService.getRoleType(username)
        );

        res.setHeader("token", token);

        res.getWriter().write(new ObjectMapper().writeValueAsString(body));
        res.getWriter().flush();
    }
}
