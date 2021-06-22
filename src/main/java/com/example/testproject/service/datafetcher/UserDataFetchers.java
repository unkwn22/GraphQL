package com.example.testproject.service.datafetcher;

import com.example.testproject.entity.User;
import com.example.testproject.repository.UserRepository;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataFetchers {
    private final UserRepository userRepository;

    public UserDataFetchers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DataFetcher<User> getUser() {
        return environment -> {
            Long id = Long.parseLong(environment.getArgument("id"));
            return userRepository.findByUserId(id);
        };
    }

    public DataFetcher<User> getUserByUsername() {
        return environment -> {
            String username = environment.getArgument("username");
            return userRepository.findByUsername(username);
        };
    }

    public DataFetcher<List<User>> getAllUsers() {
        return environment -> userRepository.findAll();
    }

    public DataFetcher<User> addUser() {
        return environment -> {
            String email = environment.getArgument("email");
            String username = environment.getArgument("username");
            String password = environment.getArgument("password");

            User user = User.builder()
                    .email(email)
                    .username(username)
                    .password(password)
                    .build();
            return userRepository.save(user);
        };
    }
}
