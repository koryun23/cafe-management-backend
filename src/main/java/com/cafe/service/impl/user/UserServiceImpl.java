package com.cafe.service.impl.user;

import com.cafe.entity.user.User;
import com.cafe.repository.UserRepository;
import com.cafe.repository.UserRoleRepository;
import com.cafe.service.core.user.UserCreationParams;
import com.cafe.service.core.user.UserService;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User create(UserCreationParams params) {
        LOGGER.info("Creating a new user according to the user creation params - {}", params);
        Assert.notNull(params, "User creation params should not be null");
        User user = userRepository.save(new User(
                params.getFirstName(),
                params.getSecondName(),
                params.getUsername(),
                LocalDateTime.now()
        ));
        LOGGER.info("Successfully created a new user according to the user creation params - {}, result - {}", params, user);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(Long id) {
        Assert.notNull(id, "user id should not be null");
        LOGGER.info("Retrieving a user having an id of {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        LOGGER.info("Successfully retrieved a user having an id of {}", id);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findById(Long id) {
        Assert.notNull(id, "user id should not be null");
        LOGGER.info("Retrieving an optional of a user having an id of {}", id);
        Optional<User> userOptional = userRepository.findById(id);
        LOGGER.info("Successfully retrieved an optional of a user having an id of {}", id);
        return userOptional;
    }
}
