package com.example.testproject.service;

import com.example.testproject.dto.CreateRequestDto;
import com.example.testproject.dto.CreateResponseDto;
import com.example.testproject.entity.User;
import com.example.testproject.exception.BadRequestException;
import com.example.testproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
