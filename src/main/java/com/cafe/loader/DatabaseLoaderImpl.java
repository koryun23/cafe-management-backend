package com.cafe.loader;

import com.cafe.entity.user.User;
import com.cafe.entity.user.UserRole;
import com.cafe.entity.user.UserRoleType;
import com.cafe.repository.UserRepository;
import com.cafe.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatabaseLoaderImpl implements DatabaseLoader, CommandLineRunner {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);


    public DatabaseLoaderImpl(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findByUsername("ADMIN").isPresent()) {
            return;
        }
        User user = new User(
                "John",
                "Williams",
                "ADMIN",
                "$2a$10$cJGbgcQ7.X7MNyW8svyvNODMwzP...lVEY1YgKv9VkjOxIyexKjDS",
                LocalDateTime.now()
        );
        userRepository.save(user);
        userRoleRepository.save(new UserRole(user, UserRoleType.MANAGER));
    }
}
