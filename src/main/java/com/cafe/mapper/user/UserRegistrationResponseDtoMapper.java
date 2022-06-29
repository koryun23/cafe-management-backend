package com.cafe.mapper.user;

import com.cafe.dto.UserRegistrationResponseDto;
import com.cafe.entity.user.User;

import java.util.function.Function;

public interface UserRegistrationResponseDtoMapper extends Function<User, UserRegistrationResponseDto> {
    UserRegistrationResponseDto apply(User user);
}
