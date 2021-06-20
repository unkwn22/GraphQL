package com.example.testproject.controller;

import com.example.testproject.dto.CreateRequestDto;
import com.example.testproject.dto.UserResponseDto;
import com.example.testproject.entity.User;
import com.example.testproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("api/user")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody CreateRequestDto createRequestDto){
        User user = userService.createUser(createRequestDto);
        UserResponseDto userResponseDto = new UserResponseDto(user);
        return ResponseEntity.ok().body(userResponseDto);
    }

    @GetMapping("api/user")
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
