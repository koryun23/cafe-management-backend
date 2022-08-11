package com.cafe.security;

import com.cafe.entity.user.UserRole;
import com.cafe.service.core.jwt.JwtService;
import com.cafe.service.core.user.UserRoleService;
import com.cafe.service.core.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtService jwtService;
    private final UserService userService;
    private final UserRoleService userRoleService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService, UserRoleService userRoleService) {
        super(authenticationManager);
        this.jwtService = jwtService;
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null) {
            token = token.substring(7);
            String username = jwtService.getUsername(token);

            if (username != null) {
                return new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        userRoleService.getRoleType(username).stream()
                                .map(userRoleType -> new SimpleGrantedAuthority(userRoleType.toString()))
                                .collect(Collectors.toList())
                );
            }

            return null;
        }

        return null;
    }
}
