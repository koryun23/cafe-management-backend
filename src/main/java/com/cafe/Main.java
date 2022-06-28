package com.cafe;

import com.cafe.dto.*;
import com.cafe.entity.user.UserRoleType;
import com.cafe.facade.core.order.OrderFacade;
import com.cafe.facade.core.product.ProductFacade;
import com.cafe.facade.core.table.CafeTableFacade;
import com.cafe.facade.core.user.UserFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        UserFacade userFacade = context.getBean(UserFacade.class);
        CafeTableFacade cafeTableFacade = context.getBean(CafeTableFacade.class);
        ProductFacade productFacade = context.getBean(ProductFacade.class);
        OrderFacade orderFacade = context.getBean(OrderFacade.class);

        System.out.println(userFacade.registerUser(new UserRegistrationRequestDto(
                "john11",
                "pwd11",
                "John",
                "Williams",
                List.of(UserRoleType.MANAGER)
        )));

        userFacade.registerUser(new UserRegistrationRequestDto(
                "mary21",
                "pwd21",
                "Mary",
                "Smith",
                List.of(UserRoleType.WAITER)
        ));

        userFacade.registerUser(new UserRegistrationRequestDto(
                "emily31",
                "pwd31",
                "Emily",
                "Smith",
                List.of(UserRoleType.WAITER)
        ));

        cafeTableFacade.register(new CafeTableRegistrationRequestDto(
                5,
                "abcd1234"
        ));

        productFacade.registerProduct(new ProductRegistrationRequestDto(
                "BoxMaster",
                9,
                400
        ));

        productFacade.registerProduct(new ProductRegistrationRequestDto(
                "Pepsi",
                3,
                700
        ));

        cafeTableFacade.assignTableToWaiter(new CafeTableAssignmentRequestDto(
                1L, 3L
        ));

        //orderFacade.register(new OrderRegistrationRequestDto(1L, "emily31"));
    }
}
