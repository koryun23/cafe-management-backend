package com.cafe.facade.impl.user;

import com.cafe.dto.*;
import com.cafe.entity.order.Order;
import com.cafe.entity.product.Product;
import com.cafe.entity.product.ProductInOrder;
import com.cafe.entity.table.CafeTable;
import com.cafe.entity.table.CafeTableAssignedToWaiter;
import com.cafe.entity.table.CafeTableStatusType;
import com.cafe.entity.user.User;
import com.cafe.entity.user.UserRoleType;
import com.cafe.facade.core.user.UserFacade;
import com.cafe.service.core.order.OrderCreationParams;
import com.cafe.service.core.order.OrderService;
import com.cafe.service.core.product.ProductCreationParams;
import com.cafe.service.core.product.ProductInOrderCreationParams;
import com.cafe.service.core.product.ProductInOrderService;
import com.cafe.service.core.product.ProductService;
import com.cafe.service.core.table.CafeTableAssignedToWaiterCreationParams;
import com.cafe.service.core.table.CafeTableAssignedToWaiterService;
import com.cafe.service.core.table.CafeTableCreationParams;
import com.cafe.service.core.table.CafeTableService;
import com.cafe.service.core.user.UserCreationParams;
import com.cafe.service.core.user.UserRoleCreationParams;
import com.cafe.service.core.user.UserRoleService;
import com.cafe.service.core.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Component
public class UserFacadeImpl implements UserFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFacadeImpl.class);
    private final UserService userService;
    private final UserRoleService userRoleService;

    public UserFacadeImpl(UserService userService,
                          UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    public UserRegistrationResponseDto registerUser(UserRegistrationRequestDto dto) {
        Assert.notNull(dto, "User registration request dto should not be null");
        LOGGER.info("Registering a new user according to the user registration request dto - {}", dto);
        User user = userService.create(new UserCreationParams(
                dto.getFirstName(),
                dto.getSecondName(),
                dto.getUsername()
        ));

        for(UserRoleType roleType : dto.getRoleList()) {
            userRoleService.create(new UserRoleCreationParams(user.getId(), roleType));
        }

        UserRegistrationResponseDto responseDto = new UserRegistrationResponseDto(
                user.getUsername(),
                user.getFirstName(),
                user.getSecondName(),
                LocalDateTime.now()
        );
        LOGGER.info("Successfully registered a new user according to the user registration request dto - {}, result - {}", dto, responseDto);
        return responseDto;
    }

}
