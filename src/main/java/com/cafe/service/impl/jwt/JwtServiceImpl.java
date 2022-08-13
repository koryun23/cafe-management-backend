package com.cafe.service.impl.jwt;

import com.cafe.entity.user.UserRole;
import com.cafe.entity.user.UserRoleType;
import com.cafe.service.core.jwt.JwtService;
import com.cafe.service.core.user.UserRoleService;
import com.cafe.service.core.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class JwtServiceImpl implements JwtService {

    private final JwtBuilder jwtBuilder;
    private final JwtParser jwtParser;
    private final UserService userService;
    private final UserRoleService userRoleService;

    public JwtServiceImpl(JwtBuilder jwtBuilder, JwtParser jwtParser, UserService userService, UserRoleService userRoleService) {
        this.jwtBuilder = jwtBuilder;
        this.jwtParser = jwtParser;
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    public String createToken(String username, List<UserRoleType> roles, Date expirationDate) {
        return jwtBuilder
                .setExpiration(expirationDate)
                .claim("tokenId", UUID.randomUUID().toString())
                .claim("username", username)
                .claim("authorities", roles)
                .compact();
    }

    @Override
    public String getUsername(String token) {
        Claims body = (Claims) jwtParser.parse(token).getBody();
        return (String) body.get("username");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getAuthorities(String token) {
        Claims body = (Claims) jwtParser.parse(token).getBody();
        List<String> authorities = (List<String>) body.get("authorities");
        System.out.println(authorities.get(0));
        return authorities;
    }
}
