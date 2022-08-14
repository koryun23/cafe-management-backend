package com.cafe;

import com.cafe.dto.request.UserRegistrationRequestDto;
import com.cafe.entity.order.Order;
import com.cafe.entity.user.UserRoleType;
import com.cafe.facade.core.user.UserFacade;
import com.cafe.repository.OrderRepository;
import com.cafe.service.core.jwt.JwtService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

@EnableJpaRepositories
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        UserFacade userFacade = context.getBean(UserFacade.class);
        JwtService jwtService = context.getBean(JwtService.class);
//
//        System.out.println(userFacade.registerUser(new UserRegistrationRequestDto(
//                "ADMIN",
//                "ADMIN",
//                "John",
//                "Williams",
//                List.of(UserRoleType.MANAGER)
//        )));
//        userFacade.registerUser(new UserRegistrationRequestDto(
//                "mary21",
//                "pwd21",
//                "Mary",
//                "Smith",
//                List.of(UserRoleType.WAITER)
//        ));

        System.out.println("---------------------------------");
        System.out.println("ADMIN");
        System.out.println(jwtService.createToken("ADMIN", List.of(UserRoleType.MANAGER), new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)));
        System.out.println("---------------------------------");
        System.out.println("mary21");
        System.out.println(jwtService.createToken("mary21", List.of(UserRoleType.WAITER), new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)));
        System.out.println("---------------------------------");
        System.out.println("emily31");
        System.out.println(jwtService.createToken("emily31", List.of(UserRoleType.WAITER), new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)));
        System.out.println("---------------------------------");
    }
}
