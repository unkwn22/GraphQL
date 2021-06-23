package com.example.testproject.service.datafetcher;

import com.example.testproject.entity.User;
import com.example.testproject.exception.BadRequestException;
import com.example.testproject.repository.UserRepository;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class UserDataFetchers {
    private final UserRepository userRepository;

    public UserDataFetchers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DataFetcher<User> addUser() {
        return environment -> {
            String email = environment.getArgument("email");
            if(userRepository.existsByEmail(email)){
                throw new BadRequestException("이미 사용중인 이메일 입니다.");
            }
            if(email.equals("")){
                throw new BadRequestException("내용이 비어 있습니다.");
            }
            String username = environment.getArgument("username");
            if(userRepository.existsByUsername(username)){
                throw new BadRequestException("이미 사용중인 닉네임 입니다.");
            }
            if(username.equals("")){
                throw new BadRequestException("내용이 비어 있습니다.");
            }
            String password = environment.getArgument("password");
            if(password.equals("")){
                throw new BadRequestException("내용이 비어 있습니다.");
            }
            User user = User.builder()
                    .email(email)
                    .username(username)
                    .password(password)
                    .build();
            return userRepository.save(user);
        };
    }

    public DataFetcher<User> updateUser(){
        return environment -> {
            String username = environment.getArgument("username");
            if(!userRepository.existsByUsername(username)){
                throw new BadRequestException("존재 하지 않는 유저네임 입니다.");
            }
            String email = environment.getArgument("email");
            if(userRepository.existsByEmail(email)){
                throw new BadRequestException("이미 사용중인 이메일 입니다.");
            }
            String password = environment.getArgument("password");
            User user = userRepository.findByUsername(username);
            user.update(email, password);
            return userRepository.save(user);
        };
    }

    public DataFetcher<User> deleteUser(){
        return environment -> {
            String username = environment.getArgument("username");
            if(!userRepository.existsByUsername(username)){
                throw new BadRequestException("존재 하지 않는 유저네임 입니다.");
            }
            User user = userRepository.findByUsername(username);
            userRepository.deleteById(user.getUserId());
            return null;
        };
    }

    public DataFetcher<User> getUser() {
        return environment -> {
            String username = environment.getArgument("username");
            if(!userRepository.existsByUsername(username)){
                throw new BadRequestException("존재 하지 않는 유저네임 입니다.");
            }
            return userRepository.findByUsername(username);
        };
    }

    public DataFetcher<List<User>> getAllUsers() {
        return environment -> userRepository.findAll();
    }
}
