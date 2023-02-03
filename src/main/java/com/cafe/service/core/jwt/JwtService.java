package com.cafe.service.core.jwt;

import com.cafe.entity.user.UserRoleType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.List;

public interface JwtService {

    String createToken(String username, List<UserRoleType> roles);

    String getUsername(String token);

    List<String> getAuthorities(String token);

    boolean isExpired(String token);

    String getRefreshToken(String username);
}
