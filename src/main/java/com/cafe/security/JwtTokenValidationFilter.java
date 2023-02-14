package com.cafe.security;

import com.cafe.service.core.jwt.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenValidationFilter extends OncePerRequestFilter {

    private JwtService jwtService;

    public JwtTokenValidationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if(token == null || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        token = token.substring(7);
        if(jwtService.isExpired(token)) {
            System.out.println("SETTING STATUS TO 401");
            response.setStatus(401);
            return;
        } else {
            String username = jwtService.getUsername(token);
            List<String> authorities = jwtService.getAuthorities(token);
            Set<SimpleGrantedAuthority> authoritySet = authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
            if(username != null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, authoritySet
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                SecurityContextHolder.getContext().setAuthentication(null);
            }
        }

        filterChain.doFilter(request, response);

    }
}
