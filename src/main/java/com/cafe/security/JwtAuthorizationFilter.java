package com.cafe.security;

import com.cafe.entity.user.UserRole;
import com.cafe.entity.user.UserRoleType;
import com.cafe.service.core.jwt.JwtService;
import com.cafe.service.core.user.UserRoleService;
import com.cafe.service.core.user.UserService;
import io.jsonwebtoken.Jwt;
import net.bytebuddy.build.Plugin;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;
    private final UserRoleService userRoleService;

    public JwtAuthorizationFilter(JwtService jwtService, UserService userService, UserRoleService userRoleService) {
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
            List<String> authorities = jwtService.getAuthorities(token);
            Set<SimpleGrantedAuthority> simpleGrantedAuthoritySet = authorities.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
            if (username != null) {
                return new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        simpleGrantedAuthoritySet
                );
            }

            return null;
        }

        return null;
    }
}
