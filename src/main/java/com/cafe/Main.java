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
        UserFacade userFacade = context.getBean(UserFacade.class);
        OrderFacade orderFacade = context.getBean(OrderFacade.class);
        ProductFacade productFacade = context.getBean(ProductFacade.class);
        CafeTableFacade cafeTableFacade = context.getBean(CafeTableFacade.class);

        System.out.println(userFacade.registerUser(new UserRegistrationRequestDto(
                "johnny11",
                "John",
                "Williams",
                List.of(UserRoleType.MANAGER)
        )));
        System.out.println(userFacade.registerUser(new UserRegistrationRequestDto(
                "mary",
                "Mary",
                "Smith",
                List.of(UserRoleType.WAITER)
        )));
        System.out.println(cafeTableFacade.register(new CafeTableRegistrationRequestDto(
                CafeTableStatusType.FREE,
                5,
                "12341234"
        )));
        System.out.println(cafeTableFacade.register(new CafeTableRegistrationRequestDto(
                CafeTableStatusType.FREE,
                5,
                "3420312"
        )));
        System.out.println(cafeTableFacade.assignTableToWaiter(new CafeTableAssignmentRequestDto(
                1L, 2L
        )));
        System.out.println(cafeTableFacade.assignTableToWaiter(new CafeTableAssignmentRequestDto(
                2L, 2L
        )));
        System.out.println(productFacade.registerProduct(new ProductRegistrationRequestDto(
                "Cola", 15, 5
        )));
        System.out.println(productFacade.registerProduct(new ProductRegistrationRequestDto(
                "box master", 10, 3
        )));
        System.out.println(orderFacade.register(
                new OrderRegistrationRequestDto(1L, OrderStatusType.OPEN
        )));
        System.out.println(orderFacade.register(
                new OrderRegistrationRequestDto(1L, OrderStatusType.OPEN)
        ));
        System.out.println(productFacade.registerProductInOrder(new ProductInOrderRegistrationRequestDto(
                1L, 1L, 1
        )));
        System.out.println(productFacade.updateProduct(new ProductUpdateRequestDto(
                1L, "Cola", 15, 3
        )));
    }
}
