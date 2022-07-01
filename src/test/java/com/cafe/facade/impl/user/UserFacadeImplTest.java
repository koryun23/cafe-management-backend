package com.cafe.facade.impl.user;

import com.cafe.dto.request.UserRegistrationRequestDto;
import com.cafe.dto.response.UserRegistrationResponseDto;
import com.cafe.entity.user.UserRoleType;
import com.cafe.facade.core.user.UserFacade;
import com.cafe.mapper.user.UserCreationParamsMapper;
import com.cafe.mapper.user.UserRegistrationResponseDtoMapper;
import com.cafe.service.core.user.UserRoleService;
import com.cafe.service.core.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserFacadeImplTest {
    private UserFacade testSubject;

    @Mock
    private UserService userService;

    @Mock
    private UserRoleService userRoleService;

    @Mock
    private UserCreationParamsMapper userCreationParamsMapper;

    @Mock
    private UserRegistrationResponseDtoMapper userRegistrationResponseDtoMapper;

    @BeforeEach
    public void init() {
        testSubject = new UserFacadeImpl(
                userService,
                userRoleService,
                userCreationParamsMapper,
                userRegistrationResponseDtoMapper
        );
    }

    @Test
    public void testRegisterUserWhenRequestDtoIsNull() {
        Assertions.assertThatThrownBy(() -> testSubject.registerUser(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testRegisterUserWithTakenCredentials() {
        Mockito.when(userService.existsByPasswordOrUsername("pwd11", "john11")).thenReturn(true);
        UserRegistrationResponseDto response = testSubject.registerUser(new UserRegistrationRequestDto(
                "john11",
                "pwd11",
                "John",
                "Smith",
                List.of(UserRoleType.MANAGER))
        );
        Assertions.assertThat(response.getErrors()).isNotNull();
        Assertions.assertThat(response.getErrors()).isEqualTo(List.of("Cannot register as on of the given credentials is already taken"));

        Mockito.verify(userService).existsByPasswordOrUsername("pwd11", "john11");
        Mockito.verifyNoMoreInteractions(userService, userRoleService, userCreationParamsMapper, userRegistrationResponseDtoMapper);
    }
}