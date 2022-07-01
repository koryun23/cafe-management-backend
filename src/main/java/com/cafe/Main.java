package com.cafe;

import com.cafe.dto.request.UserRegistrationRequestDto;
import com.cafe.entity.user.UserRoleType;
import com.cafe.facade.core.user.UserFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        UserFacade userFacade = context.getBean(UserFacade.class);

        System.out.println(userFacade.registerUser(new UserRegistrationRequestDto(
                "john11",
                "pwd11",
                "John",
                "Williams",
                List.of(UserRoleType.MANAGER)
        )));

    }
}
