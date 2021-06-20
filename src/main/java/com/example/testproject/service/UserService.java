package com.example.testproject.service;

import com.example.testproject.dto.CreateRequestDto;
import com.example.testproject.dto.UserResponseDto;
import com.example.testproject.entity.User;
import com.example.testproject.exception.BadRequestException;
import com.example.testproject.repository.UserRepository;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User createUser (CreateRequestDto createRequestDto){

        if(userRepository.existsByUsername(createRequestDto.getUsername())){
            throw new BadRequestException("사용 중인 유저 네임 입니다.");
        }

        if(userRepository.existsByEmail(createRequestDto.getEmail())){
            throw new BadRequestException("사용 중인 이메일 입니다.");
        }

        User user = User.builder()
                .email(createRequestDto.getEmail())
                .username(createRequestDto.getUsername())
                .password(createRequestDto.getPassword())
                .build();

        return userRepository.save(user);
    }

    public List<UserResponseDto> getAllUsers() {

        int userCnt = (int) userRepository.count();

        if(userCnt == 0){
            throw new InternalException("등록된 회원이 없습니다.");
        }

        List<User> users = userRepository.findAll();
        List<UserResponseDto> userList = new ArrayList<>();

        for (User value : users) {
            UserResponseDto user = new UserResponseDto(value);
            userList.add(user);
        }

        return userList;
    }
}
