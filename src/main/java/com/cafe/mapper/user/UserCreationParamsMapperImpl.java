package com.cafe.mapper.user;

import com.cafe.dto.UserRegistrationRequestDto;
import com.cafe.service.core.user.UserCreationParams;
import org.springframework.stereotype.Component;

@Component
public class UserCreationParamsMapperImpl implements UserCreationParamsMapper{
    @Override
    public UserCreationParams apply(UserRegistrationRequestDto dto) {
        return new UserCreationParams(
                dto.getFirstName(),
                dto.getSecondName(),
                dto.getUsername(),
                dto.getPassword()
        );
    }
}
