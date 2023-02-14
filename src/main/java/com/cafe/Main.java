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
    }
}
