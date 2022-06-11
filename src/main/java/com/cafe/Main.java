package com.cafe;

import com.cafe.dto.*;
import com.cafe.entity.order.OrderStatusType;
import com.cafe.entity.product.Product;
import com.cafe.entity.table.CafeTableStatusType;
import com.cafe.entity.user.UserRoleType;
import com.cafe.facade.core.order.OrderFacade;
import com.cafe.facade.core.product.ProductFacade;
import com.cafe.facade.core.table.CafeTableFacade;
import com.cafe.facade.core.user.UserFacade;
import com.cafe.service.core.user.UserCreationParams;
import com.cafe.service.core.user.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
    }
}
