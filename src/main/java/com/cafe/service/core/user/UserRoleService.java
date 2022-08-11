package com.cafe.service.core.user;

import com.cafe.entity.user.UserRole;
import com.cafe.entity.user.UserRoleType;

import java.util.List;

public interface UserRoleService {
    UserRole create(UserRoleCreationParams params);

    List<UserRoleType> getRoleType(String username);
}
